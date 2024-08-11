package ex05_BusinessLogic;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    System.err.println("UserNotFoundException");
  }
}
