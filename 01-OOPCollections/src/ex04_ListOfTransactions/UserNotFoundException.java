package ex04_ListOfTransactions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    System.err.println("UserNotFoundException");
  }
}
