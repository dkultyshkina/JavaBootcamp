package ex02_IDGenerator;

public class User {
  private int id_;
  private String name_;
  private int balance_;

  public User(String newName, int newBalance) {
    id_ = UserIdsGenerator.getInstance().generateId();
    name_ = newName;
    balance_ = newBalance;
  }

  public void setName(String newName) {
    name_ = newName;
  }

  public void setBalance(int newBalance) {
    balance_ = newBalance;
  }

  public int getId() {
    return id_;
  }

  public String getName() {
    return name_;
  }

  public int getBalance() {
    return balance_;
  }
}
