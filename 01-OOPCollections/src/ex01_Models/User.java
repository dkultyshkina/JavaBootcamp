package ex01_Models;

public class User {
  private int id_;
  private String name_;
  private int balance_;

  public User(int newId, String newName, int newBalance) {
    id_ = newId;
    name_ = newName;
    balance_ = newBalance;
  }

  public void setId(int newId) {
    id_ = newId;
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
