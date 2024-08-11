package ex01_FileSignatures;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

class SignaturesReader {
  static final String SIGNATUREFILE = "/src/ex01_FileSignatures/signatures.txt";
  private FileInputStream fileSignature_;
  private HashMap<String, String> information_ = new HashMap<>();

  public SignaturesReader(){};

  public HashMap<String, String> readSignature() {
    try {
      fileSignature_ = new FileInputStream(
          new File(".").getAbsoluteFile().getParentFile().getAbsolutePath() + SIGNATUREFILE);
      BufferedInputStream bufferedInputStreamSignature = new BufferedInputStream(fileSignature_);
      int i = 0;
      String key = "", value = "";
      boolean metka = false;
      while ((i = bufferedInputStreamSignature.read()) != -1) {
        if ((char) i == '\n') {
          information_.put(key, value);
          value = "";
          key = "";
          metka = false;
          continue;
        }
        if ((char) i == ',') {
          i = bufferedInputStreamSignature.read();
          metka = true;
          continue;
        }
        if (metka == true) {
          value += (char) i;
          continue;
        }
        key += (char) i;
      }
      bufferedInputStreamSignature.close();
      fileSignature_.close();
    } catch (IOException e) {
      System.out.println(e.toString());
    }
    return information_;
  }
}