package ex01_FileSignatures;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class FinderFileType {
  private HashMap<String, String> signature_ = new HashMap<>();
  private FileInputStream fileInput_;
  private String input_;

  public FinderFileType(HashMap<String, String> newSignature, String newInput) {
    signature_ = newSignature;
    input_ = newInput;
  };

  public String findFileType() {
    try {
      fileInput_ = new FileInputStream(input_);
      BufferedInputStream bufferedInputStreamSignature = new BufferedInputStream(fileInput_);
      int i = 0;
      String line = "";
      while ((i = bufferedInputStreamSignature.read()) != -1) {
        String tmp = String.format("%02X", (byte) i);
        line += tmp;
        if (signature_.containsValue(line)) {
          String key = findKey(line);
          if (!key.isEmpty()) {
            bufferedInputStreamSignature.close();
            fileInput_.close();
            return (key + '\n');
          }
        }
        line += " ";
      }
      bufferedInputStreamSignature.close();
      fileInput_.close();
    } catch (IOException e) {
      System.out.println("UNDEFINED");
    }
    return "";
  }

  private String findKey(String line) {
    for (HashMap.Entry<String, String> entry : signature_.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
      if (line.equals(value)) {
        return key;
      }
    }
    return "";
  }
}
