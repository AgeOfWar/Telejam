package me.palazzomichi.telegram.telejam.connection;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class HttpUrlConnection implements Closeable {
  
  private static final int CONNECTION_TIMEOUT = 0;
  private static final int READ_TIMEOUT = 0;
  
  private final HttpURLConnection connection;
  private final String charset;
  private final PrintWriter output;
  
  public HttpUrlConnection(HttpURLConnection connection, String charset) throws IOException {
    this.connection = connection;
    this.charset = charset;
    
    connection.setUseCaches(false);
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    connection.setConnectTimeout(CONNECTION_TIMEOUT);
    connection.setReadTimeout(READ_TIMEOUT);
    
    output = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), charset));
  }
  
  public HttpUrlConnection(HttpURLConnection connection) throws IOException {
    this(connection, "UTF-8");
  }
  
  public void addField(String name, String value) throws IOException {
    output.append(URLEncoder.encode(name, charset))
        .append('=')
        .append(URLEncoder.encode(value, charset))
        .append('&');
    output.flush();
  }
  
  public InputStream getInputStream() throws IOException {
    output.close();
    try {
      return connection.getInputStream();
    } catch (IOException e) {
      InputStream errorStream = connection.getErrorStream();
      if (errorStream == null) {
        throw e;
      }
      return errorStream;
    }
  }
  
  @Override
  public void close() {
    output.close();
  }
  
}
