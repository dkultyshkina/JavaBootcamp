package ex05_BusinessLogic;

public class IllegalTransactionException extends RuntimeException {
  public IllegalTransactionException() {
    System.err.println("IllegalTransactionException");
  }
}
