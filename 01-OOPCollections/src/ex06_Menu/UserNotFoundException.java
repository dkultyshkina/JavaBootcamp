package ex06_Menu;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    System.err.println("UserNotFoundException");
  }
}
