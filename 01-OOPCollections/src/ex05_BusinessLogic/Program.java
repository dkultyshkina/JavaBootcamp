package ex05_BusinessLogic;

import java.util.UUID;

public class Program {
  public static void main(String[] args) {
    TransactionsService service = new TransactionsService();
    User firstUser = new User("John", 10000);
    User secondUser = new User("Mike", 10000);
    for (int i = 1; i <= 5; ++i) {
      service.performTransferTransaction(firstUser, secondUser, (i * 100));
    }
    for (int i = 1; i <= 5; ++i) {
      service.performTransferTransaction(secondUser, firstUser, (i * 10));
    }

    System.out.println("Adding user...");
    service.addUser(firstUser);
    service.addUser(secondUser);
    System.out.println("Result after adding user: " + service.getList().getCountUser() + "\n");

    System.out.println("Retrieving a user's balance...");
    System.out.println(service.getBalance(2));
    System.out.println(service.getBalance(1));
    System.out.println();

    System.out.println("Performing a transfer transaction...");
    service.performTransferTransaction(firstUser, secondUser, 500);
    System.out.println(
        "Number of first user transactions: " + service.getTransfers(firstUser).length + "\n");
    System.out.println(
        "Number of second user transactions: " + service.getTransfers(secondUser).length + "\n");

    System.out.println("Retrieving trandfers of a sprcific user...");
    Transaction[] first = service.getTransfers(firstUser);
    Transaction[] second = service.getTransfers(secondUser);
    System.out.println("First: ");
    for (int i = 0; i < service.getTransfers(firstUser).length; ++i) {
      System.out.println(i + 1 + ". " + first[i].getId());
    }
    System.out.println("\nSecond: ");
    for (int i = 0; i < service.getTransfers(secondUser).length; ++i) {
      System.out.println(i + 1 + ". " + second[i].getId());
    }
    System.out.println();

    System.out.println("Check validity of transaction...");
    int size = service.checkValidityTransactions().length;
    System.out.println("Result of array after check: " + size + "\n");

    System.out.println("Removing a transaction...");
    first = service.getTransfers(firstUser);
    UUID id = first[0].getId();
    service.removeTransaction(id, firstUser);
    id = first[1].getId();
    service.removeTransaction(id, firstUser);
    first = service.getTransfers(firstUser);
    System.out.println("Number of first user transactions after removing: " + first.length + "\n");

    second = service.getTransfers(secondUser);
    id = second[0].getId();
    service.removeTransaction(id, secondUser);
    second = service.getTransfers(secondUser);
    System.out.println(
        "Number of second user transactions after removing: " + second.length + "\n");

    System.out.println("First: ");
    for (int i = 0; i < service.getTransfers(firstUser).length; ++i) {
      System.out.println(i + 1 + ". " + first[i].getId());
    }
    System.out.println("\nSecond: ");
    for (int i = 0; i < service.getTransfers(secondUser).length; ++i) {
      System.out.println(i + 1 + ". " + second[i].getId());
    }
    System.out.println();

    System.out.println("Check validity of transaction...");
    Transaction[] result = service.checkValidityTransactions();
    System.out.println("Result of array after check: " + result.length);
    for (int i = 0; i < result.length; i++) {
      System.out.println(result[i].getRecipient().getName() + "(id = " + result[i].getRecipient().getId()
          + ") has an unacknowledged transfer id = " + result[i].getId() + " from "
          + result[i].getSender().getName() + "(id = " + result[i].getSender().getId() + ")"
          + " for " + result[i].getTransferAmount());
    }
    System.out.println("Throwing a time exception: ");

    TransactionsService serviceTransactions = new TransactionsService();
    firstUser = new User("John", 100);
    secondUser = new User("Mike", 100);
    try {
      for (int i = 1; i <= 5; ++i) {
        serviceTransactions.performTransferTransaction(firstUser, secondUser, (i * 10));
      }
      for (int i = 1; i <= 5; ++i) {
        serviceTransactions.performTransferTransaction(secondUser, firstUser, (i * 10));
      }
    } catch (IllegalTransactionException e) {
      System.out.println(e.toString());
    }
  }
}
