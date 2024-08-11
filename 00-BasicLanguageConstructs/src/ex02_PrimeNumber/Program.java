package ex02_PrimeNumber;

import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int input = scanner.nextInt();
    int count = checkNumber(input);
    System.out.print(" " + count);
    scanner.close();
  }

  private static int checkNumber(int input) {
    if (input < 1) {
      System.err.println("Illegal Argument");
      System.exit(-1);
    }
    int count = 0;
    for (int i = 2; i <= Math.sqrt(input) + 1; ++i) {
      ++count;
      if (input % i == 0) {
        System.out.print(false);
        return count;
      }
    }
    System.out.print(true);
    return count;
  }
}