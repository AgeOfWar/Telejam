package io.github.ageofwar.telejam.updates;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.methods.GetUpdates;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.LongUnaryOperator;

/**
 * Utility class that reads new updates received from a bot.
 *
 * @author Michi Palazzo
 */
public final class UpdateReader implements AutoCloseable {
  
  private final Bot bot;
  private final ConcurrentLinkedQueue<Update> updates;
  private final LongUnaryOperator backOff;
  private long lastUpdateId;
  
  /**
   * Constructs an UpdateReader.
   *
   * @param bot     the bot that receive updates
   * @param backOff back off to be used when long polling fails
   */
  public UpdateReader(Bot bot, LongUnaryOperator backOff) {
    this.bot = Objects.requireNonNull(bot);
    this.backOff = Objects.requireNonNull(backOff);
    updates = new ConcurrentLinkedQueue<>();
    lastUpdateId = -1;
  }
  
  /**
   * Constructs an UpdateReader.
   *
   * @param bot the bot that receive updates
   */
  public UpdateReader(Bot bot) {
    this(bot, a -> 500L);
  }
  
  /**
   * Returns the number of updates that can be read from this update reader without blocking by the
   * next invocation read method for this update reader. The next invocation
   * might be the same thread or another thread.
   * If the available updates are more than {@code Integer.MAX_VALUE}, returns
   * {@code Integer.MAX_VALUE}.
   *
   * @return the number of updates that can be read from this update reader
   * without blocking by the next invocation read method
   */
  public int available() {
    return updates.size();
  }
  
  /**
   * Tells whether this stream is ready to be read.
   *
   * @return <code>true</code> if the next read() is guaranteed not to block for input,
   * <code>false</code> otherwise.  Note that returning false does not guarantee that the
   * next read will block.
   */
  public boolean ready() {
    return !updates.isEmpty();
  }
  
  /**
   * Reads one update from the stream.
   *
   * @return the read update
   * @throws IOException          if an I/O Exception occurs
   * @throws InterruptedException if any thread has interrupted the current
   *                              thread while waiting for updates
   */
  public Update read() throws IOException, InterruptedException {
    if (!ready()) {
      for (long attempts = 0; getUpdates() == 0; attempts++) {
        Thread.sleep(backOff.applyAsLong(attempts));
      }
    }
    return updates.remove();
  }
  
  /**
   * Retrieves new updates received from the bot.
   *
   * @return number of updates received
   * @throws IOException if an I/O Exception occurs
   */
  public int getUpdates() throws IOException {
    Update[] newUpdates = getUpdates(lastUpdateId + 1);
    Collections.addAll(updates, newUpdates);
    if (newUpdates.length > 0) {
      lastUpdateId = newUpdates[newUpdates.length - 1].getId();
    }
    return newUpdates.length;
  }
  
  /**
   * Discards buffered updates and all received updates.
   *
   * @throws IOException if an I/O Exception occurs
   */
  public void discardAll() throws IOException {
    Update[] newUpdate = getUpdates(-1);
    if (newUpdate.length == 1) {
      lastUpdateId = newUpdate[0].getId();
    }
    updates.clear();
  }
  
  private Update[] getUpdates(long offset) throws IOException {
    GetUpdates getUpdates = new GetUpdates()
        .offset(offset)
        .allowedUpdates();
    return bot.execute(getUpdates);
  }
  
  @Override
  public void close() throws IOException {
    try {
      Update nextUpdate = updates.peek();
      getUpdates(nextUpdate != null ? nextUpdate.getId() : lastUpdateId + 1);
      lastUpdateId = -1;
      updates.clear();
    } catch (IOException e) {
      throw new IOException("Unable to close update reader", e);
    }
  }
  
}
