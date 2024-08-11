package ex05_BusinessLogic;

import java.util.UUID;

public class TransactionsService {
  private UsersList list_;

  public TransactionsService() {
    list_ = new UsersArrayList();
  }

  public UsersList getList() {
    return list_;
  }

  public void addUser(User newUser) {
    list_.addUser(newUser);
  }

  public int getBalance(int id) {
    try {
      User user = list_.getUserId(id);
      return user.getBalance();
    } catch (UserNotFoundException e) {
      System.out.println("UserNotFoundException");
    }
    return 0;
  }

  public void performTransferTransaction(User firstUser, User secondUser, int transferAmount) {
    if (firstUser.getBalance() - transferAmount < 0) {
      throw new IllegalTransactionException();
    }
    UUID uuidTransaction = UUID.randomUUID();
    Transaction firstTransaction = new Transaction(
        uuidTransaction, firstUser, secondUser, TransferCategory.OUTCOME, transferAmount * -1);
    Transaction secondTransaction = new Transaction(
        uuidTransaction, secondUser, firstUser, TransferCategory.INCOME, transferAmount);
    firstUser.addTransaction(firstTransaction);
    secondUser.addTransaction(secondTransaction);
  }

  public Transaction[] getTransfers(User user) {
    return user.toArray();
  }

  public void removeTransaction(UUID id, User user) {
    user.removeTransaction(id);
  }

  public Transaction[] checkValidityTransactions() {
    TransactionsList list = new TransactionsLinkedList();
    for (int i = 1; i <= list_.getCountUser(); i++) {
      User user = list_.getUserId(i);
      if (user != null) {
        int size = getTransfers(user).length;
        for (int j = 0; j < size; j++) {
          list.addTransaction(user.getTransactionsList().toArray()[j]);
        }
      }
    }
    TransactionsLinkedList result = new TransactionsLinkedList();
    Transaction[] arrayFirst = list.toArray();
    if (arrayFirst != null) {
      int sizeArray = arrayFirst.length;
      for (int i = 0; i < sizeArray; i++) {
        int count = 0;
        for (int j = 0; j < sizeArray; j++) {
          if (arrayFirst[i].getId().equals(arrayFirst[j].getId())) {
            count++;
          }
        }
        if (count != 2) {
          result.addTransaction(arrayFirst[i]);
        }
      }
    }
    return result.toArray();
  }
}
