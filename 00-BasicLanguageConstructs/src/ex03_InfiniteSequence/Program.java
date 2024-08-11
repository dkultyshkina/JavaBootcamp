package ex03_InfiniteSequence;

import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int sum = 0;
    int count = 0;
    while (scanner.hasNextInt()) {
      int input = scanner.nextInt();
      if (input == 42) {
        break;
      }
      sum = countSum(input);
      count += checkNumber(sum);
    }
    System.out.print("Count of coffee-request - " + count);
    scanner.close();
  }

  private static int countSum(int input) {
    int sum = 0;
    int figure = 0;
    while (input != 0) {
      figure = input % 10;
      sum += figure;
      input /= 10;
    }
    return sum;
  }

  private static int checkNumber(int sum) {
    for (int i = 2; i <= Math.sqrt(sum) + 1; ++i) {
      if (sum % i == 0) {
        return 0;
      }
    }
    return 1;
  }
}
