package ex04_TooManyThreads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Program {
  static private int countThreads;

  public static void main(String[] args) {
    if (args.length == 0) {
      System.exit(0);
    }
    String commandLine = Arrays.toString(args);
    String[] threadsCount = commandLine.split("=");
    if (!commandLine.isEmpty() && "[--threadsCount".equals(threadsCount[0])) {
      String countThreadsStr = threadsCount[1].replace("]", "");
      countThreads = Integer.valueOf(countThreadsStr);
      startProcess();
    }
  }

  private static void startProcess() {
    ReaderFile reader = new ReaderFile();
    HashMap<Integer, String> map = reader.readFile();
    if (!map.isEmpty() && countThreads > 0 && map.size() >= countThreads) {
      int part = map.size() / countThreads;
      int i = 0;
      ArrayList<Downloader> threads = new ArrayList<Downloader>();
      for (i = 0; i < countThreads - 1; ++i) {
        threads.add(new Downloader(i + 1, map, i * part, (i + 1) * part));
      }
      threads.add(new Downloader(i + 1, map, i * part, map.size()));
      for (Downloader t : threads) {
        t.start();
      }
    }
  }
}
