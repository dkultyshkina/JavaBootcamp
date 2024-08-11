package ex01_TheSumOfTheNumbers;

public class Program {
  public static void main(String[] args) {
    int number = 479598;
    int sum = 0;
    int figure = 0;
    for (int i = 0; i < 6; ++i) {
      figure = number % 10;
      sum += figure;
      number /= 10;
    }
    System.out.print(sum);
  }
}