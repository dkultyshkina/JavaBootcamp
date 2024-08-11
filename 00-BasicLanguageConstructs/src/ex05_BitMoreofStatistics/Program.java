package ex05_BitMoreofStatistics;

import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] count = new int[65535];
    if (scanner.hasNextLine()) {
      String input = scanner.nextLine();
      char[] array = input.toCharArray();
      for (int i = 0; i < input.length(); i++) {
        count[array[i]]++;
      }
      countStatistic(count);
    }
    scanner.close();
  }

  private static void countStatistic(int[] count) {
    int[] result = new int[10];
    char[] listChar = new char[10];
    int maxCount = 0;
    char maxChar = ' ';
    int maxIndex = 0;
    for (int i = 0; i < 10; ++i) {
      maxCount = 0;
      maxChar = ' ';
      for (int j = 0; j < 65535; ++j) {
        if (maxCount < count[j]) {
          maxCount = count[j];
          maxChar = (char) j;
          maxIndex = j;
        }
      }
      result[i] = count[maxIndex];
      listChar[i] = maxChar;
      count[maxIndex] = 0;
    }
    sortResult(result, listChar);
    printStatistic(result);
    printListChar(listChar);
  }

  private static void sortResult(int[] result, char[] listChar) {
    int helper = 0;
    char helperChar = ' ';
    for (int i = 0; i < 9; ++i) {
      for (int j = i + 1; j < 10; ++j) {
        if (result[i] < result[j]) {
          helper = result[i];
          result[i] = result[j];
          result[j] = helper;
          helperChar = listChar[i];
          listChar[i] = listChar[j];
          listChar[j] = helperChar;
        }
      }
    }
  }

  private static void printStatistic(int[] result) {
    System.out.println();
    System.out.println();
    int coef = result[0];
    if (coef != 0) {
      System.out.print(result[0]);
      System.out.println();
      for (int i = 10; i > 0; i--) {
        for (int j = 0; j < 10; j++) {
          if (result[j] * 10 / coef >= i)
            System.out.print("#\t");
          if (result[j] * 10 / coef == i - 1) {
            if (result[j] != 0)
              System.out.print(result[j] + "\t");
          }
        }
        System.out.println();
      }
    } else {
      for (int i : result) {
        System.out.print(i);
      }
    }
  }

  private static void printListChar(char[] listChar) {
    for (int i = 0; i < 10; i++) {
      System.out.print(listChar[i] + "\t");
    }
  }
}
