package ex04_SomeStatistics;

import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    int[][] array = new int[18][5];
    Scanner scanner = new Scanner(System.in);
    int week = 0;
    while (week != 19) {
      if (scanner.hasNextLine()) {
        ++week;
        String input = scanner.nextLine();
        if (checkEntry(input)) {
          break;
        }
        if (validateString(input) && checkWeek(input, week)) {
          if (scanner.hasNextLine()) {
            String numbers = scanner.nextLine();
            String parts[] = numbers.split(" ");
            int j = 0;
            for (String number : parts) {
              try {
                int i = Integer.parseInt(number);
                array[week][j] = i;
                ++j;
              } catch (NumberFormatException e) {
                System.err.println("Illegal Argument");
                System.exit(-1);
              }
            }
          }
        } else {
          System.err.println("Illegal Argument");
          System.exit(-1);
        }
      }
    }
    printStatistic(array, week);
    scanner.close();
  }

  public static boolean checkEntry(String input) {
    if ("42".equals(input)) {
      return true;
    }
    return false;
  }

  public static boolean validateString(String input) {
    if (!input.isEmpty() && input.matches("Week(.*)")) {
      return true;
    }
    return false;
  }

  public static boolean checkWeek(String input, int week) {
    String parts[] = input.split(" ");
    if (parts.length > 1) {
      int i = Integer.parseInt(parts[1]);
      if (i == week) {
        return true;
      }
    }
    return false;
  }

  public static void printStatistic(int[][] array, int week) {
    for (int i = 1; i < week; ++i) {
      System.out.print("Week " + i + " ");
      int min = findMin(array[i]);
      for (int j = 1; j <= min; ++j) {
        System.out.print("=");
      }
      System.out.print(">\n");
    }
  }

  public static int findMin(int[] array) {
    int min = array[0];
    for (int number : array) {
      if (number < min) {
        min = number;
      }
    }
    return min;
  }
}
