package me.palazzomichi.telegram.telejam.connection;

import me.palazzomichi.telegram.telejam.objects.UploadFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.UUID;

import static java.net.URLConnection.guessContentTypeFromStream;

public class MultipartHttpURLConnection implements Closeable {
  
  private static final String LINE_FEED = "\r\n";
  private static final int CONNECTION_TIMEOUT = 0;
  private static final int READ_TIMEOUT = 0;
  
  private final HttpURLConnection connection;
  private final String boundary;
  private final String charset;
  
  private final PrintWriter output;
  
  public MultipartHttpURLConnection(HttpURLConnection connection, String charset) throws IOException {
    this.connection = connection;
    this.charset = charset;
    boundary = generateBoundary();
    
    connection.setUseCaches(false);
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
    connection.setConnectTimeout(CONNECTION_TIMEOUT);
    connection.setReadTimeout(READ_TIMEOUT);
    
    output = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), charset));
  }
  
  private String generateBoundary() {
    return "-=-" + UUID.randomUUID().toString() + "-=-";
  }
  
  public MultipartHttpURLConnection(HttpURLConnection connection) throws IOException {
    this(connection, "UTF-8");
  }
  
  public void addFormField(String name, String value) throws IOException {
    output.append("--").append(boundary);
    output.append(LINE_FEED);
    output.append("Content-Disposition: form-data; name=\"").append(name).append('"');
    output.append(LINE_FEED);
    output.append("Content-Type: text/plain; charset=").append(charset);
    output.append(LINE_FEED);
    output.append(LINE_FEED);
    output.append(value);
    output.append(LINE_FEED);
    output.flush();
  }
  
  public void addFilePart(String fieldName, UploadFile uploadFile) throws IOException {
    output.append("--").append(boundary);
    output.append(LINE_FEED);
    output.append("Content-Disposition: form-data; name=\"").append(fieldName).append("\"; ");
    output.append("filename=\"").append(uploadFile.getFileName()).append(String.valueOf('"'));
    output.append(LINE_FEED);
    output.append("Content-Type: ").append(guessContentTypeFromStream(uploadFile.getInputStream()));
    output.append(LINE_FEED);
    output.append("Content-Transfer-Encoding: binary");
    output.append(LINE_FEED);
    output.append(LINE_FEED);
    output.flush();
    
    byte[] buffer = new byte[4096];
    int bytesRead;
    while ((bytesRead = uploadFile.getInputStream().read(buffer)) != -1) {
      connection.getOutputStream().write(buffer, 0, bytesRead);
    }
    output.flush();
    uploadFile.getInputStream().close();
  
    output.append(LINE_FEED);
    output.flush();
  }
  
  public InputStream getInputStream() throws IOException {
    output.append(LINE_FEED);
    output.append("--").append(boundary).append("--");
    output.append(LINE_FEED);
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
