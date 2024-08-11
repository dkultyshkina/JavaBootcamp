package edu.game.logic;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;

public class ReaderProperties {
  private final Path DIRECTORY_FILE =
      Paths.get(new File(".").getAbsoluteFile().getParentFile().getAbsoluteFile()
          + "/Game/src/main/resources/");
  private final Path fileName;
  HashMap<String, String> dataFieldFromFile;

  public ReaderProperties(String newFileName) {
    dataFieldFromFile = new HashMap<>();
    fileName = Paths.get(DIRECTORY_FILE.toString(), newFileName);
  }

  public HashMap<String, String> getDataField() {
    return dataFieldFromFile;
  }

  public void readResources() {
    if (fileName.toFile().isFile()) {
      readFileWithProperties();
    }
  }

  private void readFileWithProperties() {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileName.toString()));
      String value = " ";
      while ((value = reader.readLine()) != null) {
        String[] file = value.split("=");
        try {
          dataFieldFromFile.put(file[0].trim(), file[1].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
          dataFieldFromFile.put(file[0].trim(), " ");
        }
      }
      reader.close();
    } catch (IOException e) {
      System.out.println("There was a problem reading the settings file. Please, check the file");
      System.exit(1);
    }
  }
}
