package ex06_Menu;

public class TransactionNotFoundException extends RuntimeException {
  public TransactionNotFoundException() {
    System.err.println("TransactionNotFoundException");
  }
}
