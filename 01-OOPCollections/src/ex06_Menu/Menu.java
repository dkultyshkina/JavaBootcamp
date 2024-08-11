package ex06_Menu;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
  Scanner scanner_;
  TransactionsService service_;
  boolean develop_;

  public Menu(boolean metkaDev) {
    scanner_ = new Scanner(System.in);
    service_ = new TransactionsService();
    develop_ = metkaDev;
  }

  public void printHeadDev() {
    System.out.println(
        "\n1. Add a user\n2. View user balances\n3. Perform a transfer\n4. View all transactions for a specific user\n5. DEV – remove a transfer by ID\n6. DEV – check transfer validity\n7. Finish execution");
  }

  public void printHeadStandard() {
    System.out.println(
        "\n1. Add a user\n2. View user balances\n3. Perform a transfer\n4. View all transactions for a specific user\n7. Finish execution");
  }

  public void proccessMenu() {
    int input = 0;
    // fillInformation();
    while (true) {
      if (develop_) {
        printHeadDev();
      } else {
        printHeadStandard();
      }
      if (scanner_.hasNextInt()) {
        input = scanner_.nextInt();
        checkInput(input);
      } else {
        System.out.println("Please, enter the correct number!");
        scanner_.nextLine();
      }
      System.out.println("---------------------------------------------------------");
    }
  }

  private void checkInput(int input) {
    switch (input) {
      case 1:
        try {
          addUser();
        } catch (RuntimeException e) {
          System.out.println("Error! " + e.toString() + "! Try again!");
        }
        break;
      case 2:
        try {
          viewUserBalance();
        } catch (RuntimeException e) {
          System.out.println("Error! " + e.toString() + "! Try again!");
        }
        break;
      case 3:
        try {
          performTransfer();
        } catch (RuntimeException e) {
          System.out.println("Error! " + e.toString() + "! Try again!");
        }
        break;
      case 4:
        try {
          viewAllTransactions();
        } catch (RuntimeException e) {
          System.out.println("Error! " + e.toString() + "! Try again!");
        }
        break;
      case 5:
        try {
          if (develop_) {
            removeTransferId();
          }
        } catch (RuntimeException e) {
          System.out.println("Error! " + e.toString() + "! Try again!");
        }
        break;
      case 6:
        try {
          if (develop_) {
            checkValidityTransactions();
          }
        } catch (RuntimeException e) {
          System.out.println("Error! " + e.toString() + "! Try again!");
        }
        break;
      case 7:
        System.exit(0);
      default:
        break;
    }
  }

  private void addUser() {
    System.out.println("Enter a user name and a balance");
    scanner_.nextLine();
    String input = scanner_.nextLine();
    String[] inputArray = input.split(" ");
    int amount = Integer.parseInt(inputArray[1]);
    User user = new User(inputArray[0], amount);
    service_.list_.addUser(user);
    System.out.println("User with id = " + user.getId() + " is added");
  }

  private void viewUserBalance() {
    System.out.println("Enter a user ID");
    int input = scanner_.nextInt();
    try {
      String result = service_.getBalance(input);
      System.out.println(result);
    } catch (UserNotFoundException e) {
      System.out.println("Throw in get items by id");
    }
  }

  private void performTransfer() {
    System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
    scanner_.nextLine();
    String input = scanner_.nextLine();
    String[] inputArray = input.split(" ");
    int firstId = Integer.parseInt(inputArray[0]);
    int secondId = Integer.parseInt(inputArray[1]);
    int money = Integer.parseInt(inputArray[2]);
    try {
      User firstUser = service_.list_.getUserId(firstId);
      User secondUser = service_.list_.getUserId(secondId);
      service_.performTransferTransaction(firstUser, secondUser, money);
      System.out.println("The transfer is completed");
    } catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("The transfer is not completed");
    }
  }

  private void viewAllTransactions() {
    System.out.println("Enter a user ID");
    int input = scanner_.nextInt();
    try {
      User user = service_.list_.getUserId(input);
      user.printList();
    } catch (UserNotFoundException e) {
      System.out.println("Throw in get items by id");
    }
  }

  private void removeTransferId() {
    System.out.println("Enter a user ID and transfer ID");
    scanner_.nextLine();
    String input = scanner_.nextLine();
    String[] inputArray = input.split(" ");
    int id = Integer.parseInt(inputArray[0]);
    UUID uuid = UUID.fromString(inputArray[1]);
    try {
      User user = service_.list_.getUserId(id);
      Transaction remote = user.removeTransaction(uuid);
      if (remote == null) {
        System.out.println("Transfer From " + user.getName() + "(id = " + user.getId() + ") "
            + " not removed");
        return;
      }
      System.out.println("Transfer To " + remote.getSender().getName()
          + "(id = " + remote.getSender().getId() + ") " + remote.getTransferAmount() + " removed");
    } catch (UserNotFoundException e) {
      System.out.println("Throw in get items by id");
    }
  }

  private void checkValidityTransactions() {
    System.out.println("Check results:");
    Transaction[] result = service_.checkValidityTransactions();
    if (result.length != 0) {
      for (int i = 0; i < result.length; i++) {
        System.out.println(result[i].getRecipient().getName()
            + "(id = " + result[i].getRecipient().getId()
            + ") has an unacknowledged transfer id = " + result[i].getId() + " from "
            + result[i].getSender().getName() + "(id = " + result[i].getSender().getId() + ")"
            + " for " + result[i].getTransferAmount());
      }
    } else {
      System.out.println("All transactions are correct");
    }
  }

  // private void fillInformation() {
  //   User firstUser = new User("John", 10000);
  //   User secondUser = new User("Mike", 10000);
  //   for (int i = 1; i <= 5; ++i) {
  //     service_.performTransferTransaction(firstUser, secondUser, (i * 100));
  //   }
  //   for (int i = 1; i <= 5; ++i) {
  //     service_.performTransferTransaction(secondUser, firstUser, (i * 100));
  //   }
  //   service_.addUser(firstUser);
  //   service_.addUser(secondUser);
  // }
}
