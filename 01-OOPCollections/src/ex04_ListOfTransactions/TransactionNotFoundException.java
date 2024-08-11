package ex04_ListOfTransactions;

public class TransactionNotFoundException extends RuntimeException {
  public TransactionNotFoundException() {
    System.err.println("TransactionNotFoundException");
  }
}
