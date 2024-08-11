package ex04_TooManyThreads;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

public class Downloader extends Thread {
  final String DOWNLOAD_DIRECTORY = "ex04_TooManyThreads/downloads";
  private URL url;
  private int numberThread;

  private HashMap<Integer, String> map;
  private int begin;
  private int end;

  public Downloader(
      int newNumberThread, HashMap<Integer, String> newMap, int newBegin, int newEnd) {
    numberThread = newNumberThread;
    map = newMap;
    begin = newBegin;
    end = newEnd;
  };

  @Override
  public void run() {
    downloadFiles();
  }

  private void downloadFiles() {
    createDirectory();
    for (int i = begin; i != end; i++) {
      printInfoStart(i);
      try {
        url = new URL(map.get(i));
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
      saveFiles();
      printInfoEnd(i);
    }
  }

  private void createDirectory() {
    try {
      Files.createDirectories(Paths.get(DOWNLOAD_DIRECTORY));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveFiles() {
    String filename = url.getFile();
    Path outputPath = Paths.get((new File(".").getAbsoluteFile().getParentFile().getAbsolutePath()
        + "/" + DOWNLOAD_DIRECTORY + "/" + filename.substring(filename.lastIndexOf('/') + 1)));
    try (InputStream in = url.openStream()) {
      Files.copy(in, outputPath, StandardCopyOption.REPLACE_EXISTING);
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void printInfoStart(int number) {
    System.out.println("Thread-" + numberThread + " start download file number " + (number + 1));
  }

  private void printInfoEnd(int number) {
    System.out.println("Thread-" + numberThread + " finish download file number " + (number + 1));
  }
}
