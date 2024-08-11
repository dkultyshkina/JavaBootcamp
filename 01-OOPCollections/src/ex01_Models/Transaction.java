package ex01_Models;

import java.util.UUID;

public class Transaction {
  private UUID id_;
  private User recipient_;
  private User sender_;
  private TransferCategory name_;
  private int transferAmount_;

  public Transaction(UUID newId, User newRecipient, User newSender, TransferCategory newName,
      int newTransferAmount) {
    if (checkBalance(newSender, newName) && checkTransferAmount(newName, newTransferAmount)) {
      id_ = newId;
      recipient_ = newRecipient;
      sender_ = newSender;
      name_ = newName;
      transferAmount_ = newTransferAmount;
    } else {
      System.out.println("The transaction has not been created");
    }
  }

  public void setId(UUID newId) {
    id_ = newId;
  }

  public void setRecipient(User newRecipient) {
    recipient_ = newRecipient;
  }

  public boolean setSender(User newSender) {
    if (!checkBalance(newSender, name_)) {
      return false;
    }
    sender_ = newSender;
    return true;
  }

  public void setTransferCategory(TransferCategory newName) {
    if (checkTransferAmount(newName, transferAmount_)) {
      name_ = newName;
    }
  }

  public void setTransferAmount(int newTransferAmount) {
    if (checkTransferAmount(name_, newTransferAmount)) {
      transferAmount_ = newTransferAmount;
    }
  }

  public UUID getId() {
    return id_;
  }

  public User getRecipient() {
    return recipient_;
  }

  public User getSender() {
    return sender_;
  }

  public TransferCategory getName() {
    return name_;
  }

  public int getTransferAmount() {
    return transferAmount_;
  }

  private boolean checkBalance(User sender, TransferCategory name) {
    int balance = sender.getBalance();
    if ((balance < 0 && name.name().equals("INCOME"))
        || (balance <= 0 && name.name().equals("OUTCOME"))) {
      return false;
    }
    return true;
  }

  private boolean checkTransferAmount(TransferCategory name, int transferAmount) {
    switch (name) {
      case OUTCOME:
        if (transferAmount < 0) {
          return true;
        }
        return false;
      case INCOME:
        if (transferAmount > 0) {
          return true;
        }
        return false;
      default:
        return false;
    }
  }
}
