package me.palazzomichi.telegram.telejam.objects;

import java.io.*;

/**
 * A file ready to be sent.
 */
public final class UploadFile implements Closeable {
  
  /**
   * The name of the file.
   */
  private final String fileName;
  
  /**
   * The content of the file.
   */
  private final InputStream inputStream;
  
  
  /**
   * Creates an UploadFile from the specified file.
   *
   * @param file the file to upload
   * @return the created UploadFile
   * @throws FileNotFoundException if the file does not exist,
   *                    is a directory rather than a regular file,
   *                    or for some other reason cannot be opened for
   *                    reading
   */
  public static UploadFile fromFile(File file) throws FileNotFoundException {
    return new UploadFile(file.getName(), new FileInputStream(file));
  }
  
  /**
   * Creates an UploadFile from the specified file.
   *
   * @param filePath the path of the file to upload
   * @return the created UploadFile
   * @throws FileNotFoundException if the file does not exist,
   *                    is a directory rather than a regular file,
   *                    or for some other reason cannot be opened for
   *                    reading
   */
  public static UploadFile fromFile(String filePath) throws FileNotFoundException {
    return fromFile(new File(filePath));
  }
  
  /**
   * Creates an UploadFile from the specified resource.
   *
   * @param name the resource name
   * @return the created UploadFile
   * @throws FileNotFoundException if the resource could not be found
   */
  public static UploadFile fromResource(String name) throws FileNotFoundException {
    InputStream resource = UploadFile.class.getClassLoader().getResourceAsStream(name);
    if (resource == null) {
      throw new FileNotFoundException(name);
    }
    return new UploadFile(name.substring(name.lastIndexOf('/') + 1), resource);
  }
  
  
  public UploadFile(String fileName, InputStream inputStream) {
    this.fileName = fileName;
    this.inputStream = inputStream;
  }
  
  
  @Override
  public void close() throws IOException {
    inputStream.close();
  }
  
  /**
   * Getter for property {@link #fileName}.
   *
   * @return value for property {@link #fileName}
   */
  public String getFileName() {
    return fileName;
  }
  
  /**
   * Getter for property {@link #inputStream}.
   *
   * @return value for property {@link #inputStream}
   */
  public InputStream getInputStream() {
    return inputStream;
  }
  
}
