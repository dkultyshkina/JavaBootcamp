package ex04_ListOfTransactions;

import java.util.UUID;

public class Program {
  public static void main(String[] args) {
    UUID firstUuidTransaction = UUID.randomUUID();
    UUID secondUuidTransaction = UUID.randomUUID();
    User firstUser = new User("John", 10000);
    User secondUser = new User("Mike", 10000);
    Transaction firstTransaction = new Transaction(
        firstUuidTransaction, firstUser, secondUser, TransferCategory.OUTCOME, (1 * 100 * -1));
    Transaction secondTransaction = new Transaction(
        secondUuidTransaction, secondUser, firstUser, TransferCategory.INCOME, (1 * 100));
    for (int i = 1; i <= 5; ++i) {
      firstUuidTransaction = UUID.randomUUID();
      firstTransaction = new Transaction(
          firstUuidTransaction, firstUser, secondUser, TransferCategory.OUTCOME, (i * 100 * -1));
      firstUser.addTransaction(firstTransaction);
    }
    for (int i = 1; i <= 5; ++i) {
      secondUuidTransaction = UUID.randomUUID();
      secondTransaction = new Transaction(
          secondUuidTransaction, secondUser, firstUser, TransferCategory.INCOME, (i * 100));
      secondUser.addTransaction(secondTransaction);
    }
    System.out.println("Transaction list after add:");
    System.out.println("First user:");
    firstUser.printList();
    System.out.println("Second user:");
    secondUser.printList();
    System.out.println("Transaction list after remove:");
    firstUser.removeTransaction(firstUuidTransaction);
    secondUser.removeTransaction(secondUuidTransaction);
    System.out.println("First user:");
    firstUser.printList();
    System.out.println("Second user:");
    secondUser.printList();
    System.out.println("Transaction list after remove with exception:");
    UUID uuidTransaction = UUID.randomUUID();
    try {
      firstUser.removeTransaction(uuidTransaction);
      secondUser.printList();
    } catch (TransactionNotFoundException e) {
      System.out.println(e.toString());
    }
    System.out.println("Transaction list after converting to Array:");
    Transaction[] firstArray = firstUser.toArray();
    System.out.println("First user:");
    for (int i = 0; i < 4; ++i) {
      System.out.println(firstArray[i].getSender().getName() + " -> "
          + firstArray[i].getRecipient().getName() + ", " + firstArray[i].getTransferAmount() + ", "
          + firstArray[i].getName() + ", " + firstArray[i].getId());
    }
    System.out.println("Second user:");
    Transaction[] secondArray = secondUser.toArray();
    for (int i = 0; i < 4; ++i) {
      System.out.println(secondArray[i].getSender().getName() + " -> "
          + secondArray[i].getRecipient().getName() + ", " + secondArray[i].getTransferAmount()
          + ", " + secondArray[i].getName() + ", " + secondArray[i].getId());
    }
  }
}
