package ex01_Models;

import java.util.UUID;

public class Program {
  public static void main(String[] args) {
    UUID firstUuidTransaction = UUID.randomUUID();
    UUID secondUuidTransaction = UUID.randomUUID();
    User firstUser = new User(1, "John", 500);
    User secondUser = new User(2, "Mike", 500);
    Transaction firstTransaction = new Transaction(
        firstUuidTransaction, firstUser, secondUser, TransferCategory.OUTCOME, -500);
    Transaction secondTransaction = new Transaction(
        secondUuidTransaction, secondUser, firstUser, TransferCategory.INCOME, +500);
    firstTransaction.getRecipient();
    System.out.println(firstTransaction.getSender().getName() + " -> "
        + firstTransaction.getRecipient().getName() + ", " + firstTransaction.getTransferAmount()
        + ", " + firstTransaction.getName() + ", " + firstTransaction.getId());
    System.out.println(secondTransaction.getSender().getName() + " -> "
        + secondTransaction.getRecipient().getName() + ", " + secondTransaction.getTransferAmount()
        + ", " + secondTransaction.getName() + ", " + secondTransaction.getId());
    firstUser = new User(1, "John", 0);
    secondUser = new User(2, "Mike", 0);
    firstTransaction = new Transaction(
        firstUuidTransaction, firstUser, secondUser, TransferCategory.OUTCOME, -500);
    secondTransaction = new Transaction(
        secondUuidTransaction, secondUser, firstUser, TransferCategory.INCOME, +500);
  }
}
