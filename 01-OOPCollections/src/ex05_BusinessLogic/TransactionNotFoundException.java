package ex05_BusinessLogic;

public class TransactionNotFoundException extends RuntimeException {
  public TransactionNotFoundException() {
    System.err.println("TransactionNotFoundException");
  }
}
