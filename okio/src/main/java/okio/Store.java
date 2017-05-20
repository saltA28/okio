package okio;

/*
 * Created by Hippo on 5/19/2017.
 */

import java.io.IOException;

/**
 * A data store that supports reading, writing and seeking.
 */
public interface Store extends Source, Sink {

  /**
   * Sets the position, measured from the beginning,
   * at which the next read or write occurs.
   * <p>
   * If {@code pos} is bigger than {@link #size()} and it's not supported,
   * an {@link java.io.IOException} is thrown.
   * <p>
   * If {@code pos} is negative, an {@link java.lang.IndexOutOfBoundsException} is thrown.
   */
  void seek(long position) throws IOException;

  /**
   * Returns current position, measured from the beginning.
   * It's always a nonnegative.
   */
  long tell() throws IOException;

  /**
   * Returns the size.
   */
  long size() throws IOException;
}
