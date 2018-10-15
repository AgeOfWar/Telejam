package me.palazzomichi.telegram.telejam.connection;

import me.palazzomichi.telegram.telejam.objects.UploadFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

import static java.net.URLConnection.guessContentTypeFromStream;
import static me.palazzomichi.telegram.telejam.json.Json.genericTypeOf;
import static me.palazzomichi.telegram.telejam.json.Json.toJson;

public final class Http {
  
  private static final int CONNECTION_TIMEOUT = 0;
  private static final int READ_TIMEOUT = 0;
  private static final String CHARSET = "UTF-8";
  private static final int BUFFER_SIZE = 4096;
  
  public static InputStream post(String url, Map<String, Object> parameters, Map<String, UploadFile> files)
      throws IOException {
    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
    connection.setUseCaches(false);
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.setConnectTimeout(CONNECTION_TIMEOUT);
    connection.setReadTimeout(READ_TIMEOUT);
    if (files.isEmpty()) {
      connection.setRequestProperty("Content-Type", "application/json");
      try (Writer writer = new OutputStreamWriter(connection.getOutputStream(), CHARSET)) {
        toJson(parameters, genericTypeOf(Map.class, String.class, Object.class), writer);
      }
    } else {
      String boundary = generateBoundary();
      connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
      try (PrintWriter output = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), CHARSET))) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
          String key = entry.getKey();
          Object value = entry.getValue();
          output.append("--").append(boundary);
          output.append("\r\n");
          output.append("Content-Disposition: form-data; name=\"").append(key).append('"');
          output.append("\r\n");
          output.append("Content-Type: application/json; charset=").append(CHARSET);
          output.append("\r\n");
          output.append("\r\n");
          output.append(value instanceof String ? (String) value : toJson(value));
          output.append("\r\n");
          output.flush();
        }
        for (Map.Entry<String, UploadFile> entry : files.entrySet()) {
          String key = entry.getKey();
          UploadFile value = entry.getValue();
          output.append("--").append(boundary);
          output.append("\r\n");
          output.append("Content-Disposition: form-data; name=\"").append(key).append("\"; ");
          output.append("filename=\"").append(value.getFileName()).append(String.valueOf('"'));
          output.append("\r\n");
          String contentType = guessContentTypeFromStream(value.getInputStream());
          if (contentType != null) {
            output.append("Content-Type: ").append(contentType);
            output.append("\r\n");
          }
          output.append("Content-Transfer-Encoding: binary");
          output.append("\r\n");
          output.append("\r\n");
          output.flush();
  
          byte[] buffer = new byte[BUFFER_SIZE];
          int bytesRead;
          while ((bytesRead = value.getInputStream().read(buffer)) != -1) {
            connection.getOutputStream().write(buffer, 0, bytesRead);
          }
          output.flush();
          value.getInputStream().close();
  
          output.append("\r\n");
          output.flush();
        }
        output.append("\r\n");
        output.append("--").append(boundary).append("--");
        output.append("\r\n");
      }
    }
    return getInputOrErrorStream(connection);
  }
  
  private static String generateBoundary() {
    return "-=-" + UUID.randomUUID() + "-=-";
  }
  
  private static InputStream getInputOrErrorStream(HttpURLConnection connection) throws IOException {
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
  
  private Http() {
    throw new AssertionError();
  }
  
}
