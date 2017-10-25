package me.palazzomichi.telegram.telejam.util.stream;

import me.palazzomichi.telegram.telejam.TelegramConnection;
import me.palazzomichi.telegram.telejam.methods.GetUpdates;
import me.palazzomichi.telegram.telejam.objects.updates.Update;

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
public final class UpdateReader {

  private final TelegramConnection connection;
  private final ConcurrentLinkedQueue<Update> updates;
  private LongUnaryOperator backOff;
  private long lastUpdateId;
  private String[] allowedUpdates;

  /**
   * Constructs an UpdateReader.
   *
   * @param connection the connection that receive updates
   * @param backOff    back off to be used when long polling fails
   */
  public UpdateReader(TelegramConnection connection, LongUnaryOperator backOff) {
    this.connection = Objects.requireNonNull(connection);
    this.backOff = Objects.requireNonNull(backOff);
    updates = new ConcurrentLinkedQueue<>();
    lastUpdateId = -1;
    allowedUpdates = new String[0];
  }

  /**
   * Constructs an UpdateReader.
   *
   * @param connection the connection that receive updates
   * @param backOff    back off to be used when long polling fails
   */
  public UpdateReader(TelegramConnection connection, long backOff) {
    this(connection, a -> backOff);
  }


  /**
   * Returns the number of updates that can be read from this update reader without blocking by the
   * next invocation read method for this update reader. The next invocation
   * might be the same thread or another thread.
   * If the available updates are more than {@code Integer.MAX_VALUE}, returns
   * {@code Integer.MAX_VALUE}.
   *
   * <p>Beware that, this method is <em>NOT</em> a constant-time operation.
   * Determining the current number of available updates requires an O(n) traversal.
   * Additionally, if elements are added or removed during execution
   * of this method, the returned result may be inaccurate.  Thus,
   * this method is typically not very useful in concurrent
   * applications.</p>
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
      for (long i = 1; getUpdates() == 0; ) {
        Thread.sleep(backOff.applyAsLong(i));
        if (i < Long.MAX_VALUE) i++;
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
    GetUpdates getUpdates = new GetUpdates()
        .offset(lastUpdateId + 1)
        .allowedUpdates(allowedUpdates);

    Update[] newUpdates = connection.execute(getUpdates);

    Collections.addAll(updates, newUpdates);

    if (newUpdates.length > 0)
      lastUpdateId = newUpdates[newUpdates.length - 1].getId();

    return newUpdates.length;
  }

  /**
   * Discards buffered updates and all received updates.
   *
   * @throws IOException if an I/O Exception occurs
   */
  public void reset() throws IOException {
    GetUpdates getUpdates = new GetUpdates()
        .offset(-1L)
        .allowedUpdates(allowedUpdates);
    
    Update[] update = connection.execute(getUpdates);
    
    if (update.length == 1) {
      lastUpdateId = update[0].getId();
    }
  
    updates.clear();
  }

  /**
   * Getter for property {@link #connection}.
   *
   * @return value for property {@link #connection}
   */
  public TelegramConnection getConnection() {
    return connection;
  }

  /**
   * Getter for property {@link #backOff}.
   *
   * @return value for property {@link #backOff}
   */
  public LongUnaryOperator getBackOff() {
    return backOff;
  }

  /**
   * Setter for property {@link #backOff}.
   *
   * @param backOff value for property {@link #backOff}
   */
  public void setBackOff(LongUnaryOperator backOff) {
    this.backOff = Objects.requireNonNull(backOff);
  }

  /**
   * Setter for property {@link #backOff}.
   *
   * @param backOff value for property {@link #backOff}
   */
  public void setBackOff(long backOff) {
    this.backOff = a -> backOff;
  }

  /**
   * Getter for property {@link #lastUpdateId}.
   *
   * @return value for property {@link #lastUpdateId}
   */
  public long getLastUpdateId() {
    return lastUpdateId;
  }

  /**
   * Setter for property {@link #allowedUpdates}.
   *
   * @param allowedUpdates value for property {@link #allowedUpdates}
   */
  public void setAllowedUpdates(String... allowedUpdates) {
    this.allowedUpdates = allowedUpdates;
  }

}
