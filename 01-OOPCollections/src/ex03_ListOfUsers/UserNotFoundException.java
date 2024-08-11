package ex03_ListOfUsers;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    System.err.println("UserNotFoundException");
  }
}
