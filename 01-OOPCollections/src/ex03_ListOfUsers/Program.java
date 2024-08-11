package ex03_ListOfUsers;

public class Program {
  public static void main(String[] args) {
    UsersList list = new UsersArrayList();
    User[] users = new User[21];
    for (int i = 0; i < 20; ++i) {
      users[i] = new User("User", i * 10);
      list.addUser(users[i]);
    }
    System.out.println("Get items by index");
    for (int i = 0; i < 20; ++i) {
      System.out.println(i + ". " + list.getUserIndex(i).convertToString());
    }
    System.out.println("Count: " + list.getCountUser());
    System.out.println("Throwing an exception at id and index 21: ");
    try {
      list.getUserId(21);
    } catch (UserNotFoundException e) {
      System.out.println("Throw in get items by id");
    }
    try {
      list.getUserIndex(21);
    } catch (UserNotFoundException e) {
      System.out.println("Throw in get items by index");
    }
  }
}
