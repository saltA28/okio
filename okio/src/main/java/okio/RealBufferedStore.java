package okio;

/*
 * Created by Hippo on 5/19/2017.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

final class RealBufferedStore implements BufferedStore {

  private static final boolean STATE_READ = false;
  private static final boolean STATE_WRITE = true;

  private final Store store;
  private final BufferedSource source;
  private final BufferedSink sink;
  private boolean state = STATE_READ;

  RealBufferedStore(Store store) {
    if (store == null) throw new NullPointerException("store == null");
    this.store = store;
    this.source = new RealBufferedSource(store);
    this.sink = new RealBufferedSink(store);
  }

  private void switchToRead() throws IOException {
    if (state == STATE_WRITE) {
      // Flush the buffer of the sink
      sink.emit();
      state = STATE_READ;
    }
  }

  private void switchToWrite() throws IOException {
    if (state == STATE_READ) {
      // Clear the buffer of the source, seek store back
      Buffer buffer = source.buffer();
      if (buffer.size != 0) {
        store.seek(store.tell() - buffer.size);
        buffer.clear();
      }
      state = STATE_WRITE;
    }
  }

  ///////////////////////////////////////////////////////////////////////////
  // Source
  ///////////////////////////////////////////////////////////////////////////

  @Override
  public long read(Buffer sink, long byteCount) throws IOException {
    switchToRead();
    return source.read(sink, byteCount);
  }

  ///////////////////////////////////////////////////////////////////////////
  // BufferedSource
  ///////////////////////////////////////////////////////////////////////////

  @Override
  public boolean exhausted() throws IOException {
    switchToRead();
    return source.exhausted();
  }

  @Override
  public void require(long byteCount) throws IOException {
    switchToRead();
    source.require(byteCount);
  }

  @Override
  public boolean request(long byteCount) throws IOException {
    switchToRead();
    return source.request(byteCount);
  }

  @Override
  public byte readByte() throws IOException {
    switchToRead();
    return source.readByte();
  }

  @Override
  public short readShort() throws IOException {
    switchToRead();
    return source.readShort();
  }

  @Override
  public short readShortLe() throws IOException {
    switchToRead();
    return source.readShortLe();
  }

  @Override
  public int readInt() throws IOException {
    switchToRead();
    return source.readInt();
  }

  @Override
  public int readIntLe() throws IOException {
    switchToRead();
    return source.readIntLe();
  }

  @Override
  public long readLong() throws IOException {
    switchToRead();
    return source.readLong();
  }

  @Override
  public long readLongLe() throws IOException {
    switchToRead();
    return source.readLongLe();
  }

  @Override
  public long readDecimalLong() throws IOException {
    switchToRead();
    return source.readDecimalLong();
  }

  @Override
  public long readHexadecimalUnsignedLong() throws IOException {
    switchToRead();
    return source.readHexadecimalUnsignedLong();
  }

  @Override
  public void skip(long byteCount) throws IOException {
    switchToRead();
    source.skip(byteCount);
  }

  @Override
  public ByteString readByteString() throws IOException {
    switchToRead();
    return source.readByteString();
  }

  @Override
  public ByteString readByteString(long byteCount) throws IOException {
    switchToRead();
    return source.readByteString(byteCount);
  }

  @Override
  public int select(Options options) throws IOException {
    switchToRead();
    return source.select(options);
  }

  @Override
  public byte[] readByteArray() throws IOException {
    switchToRead();
    return source.readByteArray();
  }

  @Override
  public byte[] readByteArray(long byteCount) throws IOException {
    switchToRead();
    return source.readByteArray(byteCount);
  }

  @Override
  public int read(byte[] sink) throws IOException {
    switchToRead();
    return source.read(sink);
  }

  @Override
  public void readFully(byte[] sink) throws IOException {
    switchToRead();
    source.readFully(sink);
  }

  @Override
  public int read(byte[] sink, int offset, int byteCount) throws IOException {
    switchToRead();
    return source.read(sink, offset, byteCount);
  }

  @Override
  public void readFully(Buffer sink, long byteCount) throws IOException {
    switchToRead();
    source.readFully(sink, byteCount);
  }

  @Override
  public long readAll(Sink sink) throws IOException {
    switchToRead();
    return source.readAll(sink);
  }

  @Override
  public String readUtf8() throws IOException {
    switchToRead();
    return source.readUtf8();
  }

  @Override
  public String readUtf8(long byteCount) throws IOException {
    switchToRead();
    return source.readUtf8(byteCount);
  }

  @Nullable
  @Override
  public String readUtf8Line() throws IOException {
    switchToRead();
    return source.readUtf8Line();
  }

  @Override
  public String readUtf8LineStrict() throws IOException {
    switchToRead();
    return source.readUtf8LineStrict();
  }

  @Override
  public String readUtf8LineStrict(long limit) throws IOException {
    switchToRead();
    return source.readUtf8LineStrict(limit);
  }

  @Override
  public int readUtf8CodePoint() throws IOException {
    switchToRead();
    return source.readUtf8CodePoint();
  }

  @Override
  public String readString(Charset charset) throws IOException {
    switchToRead();
    return source.readString(charset);
  }

  @Override
  public String readString(long byteCount, Charset charset) throws IOException {
    switchToRead();
    return source.readString(byteCount, charset);
  }

  @Override
  public long indexOf(byte b) throws IOException {
    switchToRead();
    return source.indexOf(b);
  }

  @Override
  public long indexOf(byte b, long fromIndex) throws IOException {
    switchToRead();
    return source.indexOf(b, fromIndex);
  }

  @Override
  public long indexOf(byte b, long fromIndex, long toIndex) throws IOException {
    switchToRead();
    return source.indexOf(b, fromIndex, toIndex);
  }

  @Override
  public long indexOf(ByteString bytes) throws IOException {
    switchToRead();
    return source.indexOf(bytes);
  }

  @Override
  public long indexOf(ByteString bytes, long fromIndex) throws IOException {
    switchToRead();
    return source.indexOf(bytes, fromIndex);
  }

  @Override
  public long indexOfElement(ByteString targetBytes) throws IOException {
    switchToRead();
    return source.indexOfElement(targetBytes);
  }

  @Override
  public long indexOfElement(ByteString targetBytes, long fromIndex) throws IOException {
    switchToRead();
    return source.indexOfElement(targetBytes, fromIndex);
  }

  @Override
  public boolean rangeEquals(long offset, ByteString bytes) throws IOException {
    switchToRead();
    return source.rangeEquals(offset, bytes);
  }

  @Override
  public boolean rangeEquals(long offset, ByteString bytes, int bytesOffset, int byteCount)
      throws IOException {
    switchToRead();
    return source.rangeEquals(offset, bytes, bytesOffset, byteCount);
  }

  @Override
  public InputStream inputStream() {
    return new InputStream() {
      private InputStream inputStream = source.inputStream();

      @Override
      public int read() throws IOException {
        switchToRead();
        return inputStream.read();
      }

      @Override
      public int read(byte[] b, int off, int len) throws IOException {
        switchToRead();
        return inputStream.read(b, off, len);
      }

      @Override
      public int available() throws IOException {
        switchToRead();
        return inputStream.available();
      }

      @Override
      public void close() throws IOException {
        try {
          switchToRead();
        } finally {
          try {
            inputStream.close();
          } finally {
            RealBufferedStore.this.close();
          }
        }
      }

      @Override
      public String toString() {
        return RealBufferedStore.this + ".inputStream()";
      }
    };
  }

  ///////////////////////////////////////////////////////////////////////////
  // Sink
  ///////////////////////////////////////////////////////////////////////////

  @Override
  public void write(Buffer source, long byteCount) throws IOException {
    switchToWrite();
    sink.write(source, byteCount);
  }

  @Override
  public void flush() throws IOException {
    switchToWrite();
    sink.flush();
  }

  ///////////////////////////////////////////////////////////////////////////
  // BufferedSink
  ///////////////////////////////////////////////////////////////////////////

  @Override
  public BufferedSink write(ByteString byteString) throws IOException {
    switchToWrite();
    return sink.write(byteString);
  }

  @Override
  public BufferedSink write(byte[] source) throws IOException {
    switchToWrite();
    return sink.write(source);
  }

  @Override
  public BufferedSink write(byte[] source, int offset, int byteCount) throws IOException {
    switchToWrite();
    return sink.write(source, offset, byteCount);
  }

  @Override
  public long writeAll(Source source) throws IOException {
    switchToWrite();
    return sink.writeAll(source);
  }

  @Override
  public BufferedSink write(Source source, long byteCount) throws IOException {
    switchToWrite();
    return sink.write(source, byteCount);
  }

  @Override
  public BufferedSink writeUtf8(String string) throws IOException {
    switchToWrite();
    return sink.writeUtf8(string);
  }

  @Override
  public BufferedSink writeUtf8(String string, int beginIndex, int endIndex) throws IOException {
    switchToWrite();
    return sink.writeUtf8(string, beginIndex, endIndex);
  }

  @Override
  public BufferedSink writeUtf8CodePoint(int codePoint) throws IOException {
    switchToWrite();
    return sink.writeUtf8CodePoint(codePoint);
  }

  @Override
  public BufferedSink writeString(String string, Charset charset) throws IOException {
    switchToWrite();
    return sink.writeString(string, charset);
  }

  @Override
  public BufferedSink writeString(String string, int beginIndex, int endIndex, Charset charset)
      throws IOException {
    switchToWrite();
    return sink.writeString(string, beginIndex, endIndex, charset);
  }

  @Override
  public BufferedSink writeByte(int b) throws IOException {
    switchToWrite();
    return sink.writeByte(b);
  }

  @Override
  public BufferedSink writeShort(int s) throws IOException {
    switchToWrite();
    return sink.writeShort(s);
  }

  @Override
  public BufferedSink writeShortLe(int s) throws IOException {
    switchToWrite();
    return sink.writeShortLe(s);
  }

  @Override
  public BufferedSink writeInt(int i) throws IOException {
    switchToWrite();
    return sink.writeInt(i);
  }

  @Override
  public BufferedSink writeIntLe(int i) throws IOException {
    switchToWrite();
    return sink.writeIntLe(i);
  }

  @Override
  public BufferedSink writeLong(long v) throws IOException {
    switchToWrite();
    return sink.writeLong(v);
  }

  @Override
  public BufferedSink writeLongLe(long v) throws IOException {
    switchToWrite();
    return sink.writeLongLe(v);
  }

  @Override
  public BufferedSink writeDecimalLong(long v) throws IOException {
    switchToWrite();
    return sink.writeDecimalLong(v);
  }

  @Override
  public BufferedSink writeHexadecimalUnsignedLong(long v) throws IOException {
    switchToWrite();
    return sink.writeHexadecimalUnsignedLong(v);
  }

  @Override
  public BufferedSink emit() throws IOException {
    switchToWrite();
    return sink.emit();
  }

  @Override
  public BufferedSink emitCompleteSegments() throws IOException {
    switchToWrite();
    return sink.emitCompleteSegments();
  }

  @Override
  public OutputStream outputStream() {
    return new OutputStream() {
      private OutputStream outputStream = sink.outputStream();

      @Override
      public void write(int b) throws IOException {
        switchToWrite();
        outputStream.write(b);
      }

      @Override
      public void write(byte[] data, int offset, int byteCount) throws IOException {
        switchToWrite();
        outputStream.write(data, offset, byteCount);
      }

      @Override
      public void flush() throws IOException {
        switchToWrite();
        outputStream.flush();
      }

      @Override
      public void close() throws IOException {
        try {
          switchToWrite();
        } finally {
          try {
            outputStream.close();
          } finally {
            RealBufferedStore.this.close();
          }
        }
      }

      @Override
      public String toString() {
        return RealBufferedStore.this + ".outputStream()";
      }
    };
  }

  ///////////////////////////////////////////////////////////////////////////
  // Store
  ///////////////////////////////////////////////////////////////////////////

  @Override
  public void seek(long position) throws IOException {
    if (state == STATE_READ) {
      source.buffer().clear();
    } else {
      sink.emit();
    }
    store.seek(position);
  }

  @Override
  public long tell() throws IOException {
    if (state == STATE_READ) {
      return store.tell() - source.buffer().size();
    } else {
      return store.tell() + sink.buffer().size();
    }
  }

  @Override
  public long size() throws IOException {
    if (state == STATE_WRITE) {
      sink.emit();
    }
    return store.size();
  }

  ///////////////////////////////////////////////////////////////////////////
  // Common
  ///////////////////////////////////////////////////////////////////////////

  @Override
  public Timeout timeout() {
    // Not supported
    return null;
  }

  @Override
  public void close() throws IOException {
    try {
      if (state == STATE_READ) {
        try {
          source.close();
        } finally {
          sink.close();
        }
      } else {
        try {
          sink.close();
        } finally {
          source.close();
        }
      }
    } finally {
      store.close();
    }
  }

  ///////////////////////////////////////////////////////////////////////////
  // Buffered
  ///////////////////////////////////////////////////////////////////////////

  @Override
  public Buffer buffer() {
    if (state == STATE_READ) {
      return source.buffer();
    } else {
      return sink.buffer();
    }
  }
}
