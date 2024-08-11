package ex04_ListOfTransactions;

import java.util.UUID;

public class User {
  private int id_;
  private String name_;
  private int balance_;
  private TransactionsList list_;

  public User(String newName, int newBalance) {
    id_ = UserIdsGenerator.getInstance().generateId();
    name_ = newName;
    balance_ = newBalance;
    list_ = new TransactionsLinkedList();
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

  public String convertToString() {
    return (id_ + " " + name_ + " " + balance_);
  }

  public void printList() {
    list_.printTransactionsList();
  }

  public void addTransaction(Transaction newTransaction) {
    list_.addTransaction(newTransaction);
    balance_ += newTransaction.getTransferAmount();
  }

  public void removeTransaction(UUID id) {
    list_.removeTransaction(id);
  }

  public Transaction[] toArray() {
    Transaction[] transactions = list_.toArray();
    return transactions;
  }
}
