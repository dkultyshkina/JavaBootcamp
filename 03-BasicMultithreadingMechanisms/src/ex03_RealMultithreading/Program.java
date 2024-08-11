package ex03_RealMultithreading;

import java.util.ArrayList;
import java.util.Arrays;

public class Program {
  static private int sizeArray;
  static private int countThreads;

  public static void main(String[] args) {
    if (args.length == 0 || args.length == 1) {
      System.exit(0);
    }
    String commandLine = Arrays.toString(args);
    String[] line = commandLine.split(" ");
    String[] arraySize = line[0].split("=");
    String[] threadsCount = line[1].split("=");
    if (!commandLine.isEmpty() && "[--arraySize".equals(arraySize[0])
        && "--threadsCount".equals(threadsCount[0])) {
      String countArraySizeStr = arraySize[1].replace("]", "").replace(",", "");
      String countThreadsStr = threadsCount[1].replace("]", "");
      sizeArray = Integer.valueOf(countArraySizeStr);
      countThreads = Integer.valueOf(countThreadsStr);
      startProcess();
    }
  }

  private static void startProcess() {
    Array array = new Array(sizeArray);
    ArrayList<Integer> randomArray = array.formArray();
    if (!randomArray.isEmpty() && countThreads > 0 && countThreads <= sizeArray) {
      array.sumElement();
      int part = sizeArray / countThreads;
      int i = 0;
      ArrayList<SummationArray> threads = new ArrayList<SummationArray>();
      for (i = 0; i < countThreads - 1; ++i) {
        threads.add(new SummationArray(i + 1, randomArray, i * part, (i + 1) * part));
      }
      threads.add(new SummationArray(i, randomArray, i * part, sizeArray));
      threads.add(new SummationArray(0, randomArray, 0, sizeArray));
      for (SummationArray t : threads) {
        t.start();
        try {
          t.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
          Thread.currentThread().interrupt();
          return;
        }
      }
    }
  }
}
