package ex04_ListOfTransactions;

public interface UsersList {
  public void addUser(User newUser);
  public User getUserId(int id);
  public User getUserIndex(int index);
  public int getCountUser();
}
