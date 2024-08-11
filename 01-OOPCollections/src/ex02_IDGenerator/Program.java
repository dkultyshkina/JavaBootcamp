package ex02_IDGenerator;

public class Program {
  public static void main(String[] args) {
    User firstUser = new User("John", 500);
    User secondUser = new User("Mike", 500);
    User thirdUser = new User("Anna", 500);
    System.out.println(
        firstUser.getId() + " " + firstUser.getName() + " " + firstUser.getBalance());
    System.out.println(
        secondUser.getId() + " " + secondUser.getName() + " " + secondUser.getBalance());
    System.out.println(
        thirdUser.getId() + " " + thirdUser.getName() + " " + thirdUser.getBalance());
  }
}
