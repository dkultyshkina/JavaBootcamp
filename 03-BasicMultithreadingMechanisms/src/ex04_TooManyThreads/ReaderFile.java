package ex04_TooManyThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReaderFile {
  private static final String FILE_URL =
      new File(".").getAbsoluteFile().getParentFile().getAbsolutePath() + "/ex04_TooManyThreads/files_urls.txt";
  private HashMap<Integer, String> map;

  public ReaderFile() {
    map = new HashMap<>();
  };

  public HashMap<Integer, String> readFile() {
    try {
      int number = 0;
      BufferedReader reader = new BufferedReader(new FileReader(FILE_URL));
      String value = "";
      while ((value = reader.readLine()) != null) {
        String[] url = value.split(" ");
        map.put(number, url[1]);
        ++number;
      }
      reader.close();
    } catch (IOException e) {
      System.out.println(e.toString());
    }
    return map;
  }
}
