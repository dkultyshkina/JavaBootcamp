package ex03_RealMultithreading;

import java.util.ArrayList;

public class SummationArray extends Thread {
  private ArrayList<Integer> array;
  private int numberThread;
  private int begin;
  private int end;
  private long sum;

  public SummationArray(
      int newNumberThread, ArrayList<Integer> newArray, int newBegin, int newEnd) {
    array = newArray;
    numberThread = newNumberThread;
    begin = newBegin;
    end = newEnd;
  }

  public long getSum() {
    return sum;
  }

  @Override
  public void run() {
    sumElement();
  }

  private void sumElement() {
    sum = 0;
    for (int i = begin; i < end; i++) {
      sum += array.get(i);
    }
    printInfo();
  }

  private void printInfo() {
    if (numberThread == 0) {
      System.out.println("Thread by threads: " + sum);
    } else {
      System.out.println(
          "Thread " + numberThread + ": from " + begin + " to " + end + " sum is " + sum);
    }
  }
}
