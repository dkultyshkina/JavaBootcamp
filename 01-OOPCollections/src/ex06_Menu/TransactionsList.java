package ex06_Menu;

import java.util.UUID;

public interface TransactionsList {
  public void addTransaction(Transaction transaction);
  public Transaction removeTransaction(UUID id);
  public Transaction[] toArray();
  public void printTransactionsList();
}
