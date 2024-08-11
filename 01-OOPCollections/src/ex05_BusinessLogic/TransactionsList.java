package ex05_BusinessLogic;

import java.util.UUID;

public interface TransactionsList {
  public void addTransaction(Transaction transaction);
  public void removeTransaction(UUID id);
  public Transaction[] toArray();
  public void printTransactionsList();
}
