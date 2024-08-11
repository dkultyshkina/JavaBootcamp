package ex01_FileSignatures;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
  static final String FILE = "/src/ex01_FileSignatures/result.txt";
  private FileOutputStream fileOutput_;
  private String output_;

  public FileWriter(String newOutput) {
    output_ = newOutput;
  };

  public String writeFileType() {
    try {
      fileOutput_ = new FileOutputStream(
          new File(".").getAbsoluteFile().getParentFile().getAbsolutePath() + FILE, true);
      byte[] array = output_.getBytes();
      fileOutput_.write(array);
      System.out.println("PROCESSED");
      fileOutput_.close();
    } catch (IOException e) {
      System.out.println("UNDEFINED");
    }
    return "";
  }
}
