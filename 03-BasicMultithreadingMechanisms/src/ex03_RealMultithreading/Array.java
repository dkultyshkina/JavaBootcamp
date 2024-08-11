package ex03_RealMultithreading;

import java.util.ArrayList;
import java.util.Random;

public class Array {
  private ArrayList<Integer> list;
  private int size;

  private final int MIN_SIZE = 0;
  private final int MAX_SIZE = 2000000;

  public Array(int newSize) {
    size = newSize;
    list = new ArrayList<Integer>();
  }

  public ArrayList<Integer> formArray() {
    Random random = new Random();
    if (size > MIN_SIZE && size <= MAX_SIZE) {
      for (int i = 0; i < size; i++) {
        list.add(random.nextInt(2000) - 1000);
      }
    }
    return list;
  }

  public void sumElement() {
    long sum = 0;
    for (int i = 0; i < size; ++i) {
      sum += list.get(i);
    }
    System.out.println("Sum: " + sum);
    // printArray();
  }

  // private void printArray() {
  //   for (int i=0; i < size; ++i) {
  //     System.out.println(list.get(i));
  //   }
  // }
}
