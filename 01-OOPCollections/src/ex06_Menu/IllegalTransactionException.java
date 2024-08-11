package ex06_Menu;

public class IllegalTransactionException extends RuntimeException {
  public IllegalTransactionException() {
    System.err.println("IllegalTransactionException");
  }
}
