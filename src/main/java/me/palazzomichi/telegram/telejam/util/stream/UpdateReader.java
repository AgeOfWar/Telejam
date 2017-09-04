package me.palazzomichi.telegram.telejam.util.stream;

import me.palazzomichi.telegram.telejam.TelegramConnection;
import me.palazzomichi.telegram.telejam.methods.GetUpdates;
import me.palazzomichi.telegram.telejam.objects.updates.Update;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.function.LongUnaryOperator;

/**
 * Utility class that reads new updates of a bot.
 *
 * @author Michi Palazzo
 */
public final class UpdateReader {

  private final TelegramConnection connection;
  private final Queue<Update> updates;
  private LongUnaryOperator backOff;
  private long lastUpdateId;

  /**
   * Constructs an UpdateReader.
   *
   * @param connection the connection that receive updates
   * @param backOff    back off to be used when long polling fails
   */
  public UpdateReader(TelegramConnection connection, LongUnaryOperator backOff) {
    this.connection = Objects.requireNonNull(connection);
    this.backOff = Objects.requireNonNull(backOff);
    updates = new LinkedList<>();
    lastUpdateId = -1;
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
   * Returns the number of updates that can be read from this input stream without blocking by the
   * next invocation read method for this update reader. The next invocation
   * might be the same thread or another thread.
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
   * @return True if the next read() is guaranteed not to block for input,
   * false otherwise.  Note that returning false does not guarantee that the
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
        .allowedUpdates();

    Update[] newUpdates = connection.execute(getUpdates);

    Collections.addAll(updates, newUpdates);

    if (newUpdates.length > 0)
      lastUpdateId = newUpdates[newUpdates.length - 1].getId();

    return newUpdates.length;
  }

  /**
   * Discards all the buffered updates.
   *
   * @return the number of discarded updates
   */
  public int discard() {
    int size = updates.size();
    updates.clear();
    return size;
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
    this.backOff = backOff;
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

}
