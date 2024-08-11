package ex06_Menu;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
  private Node first_;
  private Node last_;
  private int size_;

  public TransactionsLinkedList() {
    size_ = 0;
    first_ = null;
    last_ = null;
  };

  public int getSize() {
    return size_;
  }

  public void addTransaction(Transaction transaction) {
    ++size_;
    if (first_ == null) {
      first_ = new Node(transaction, null, null);
      last_ = first_;
      return;
    }
    Node penultimate = last_;
    last_ = new Node(transaction, penultimate, null);
    penultimate.next_ = last_;
    if (first_.next_ == null) {
      first_.next_ = last_;
    }
  };

  public Transaction removeTransaction(UUID id) {
    if (size_ != 0) {
      Node middle = first_;
      UUID ind = middle.value_.getId();
      while (!id.equals(ind)) {
        middle = middle.next_;
        if (middle == null) {
          throw new TransactionNotFoundException();
        }
        ind = middle.value_.getId();
      }
      if (!id.equals(ind)) {
        throw new TransactionNotFoundException();
      }
      Node next = middle.next_;
      Node prev = middle.prev_;
      --size_;
      int value = middle.value_.getTransferAmount();
      User firstUser = middle.value_.getSender();
      User secondUser = middle.value_.getRecipient();
      firstUser.setBalance(firstUser.getBalance() + (value * (-1)));
      secondUser.setBalance(secondUser.getBalance() - (value * (-1)));
      if (next == null) {
        prev.next_ = null;
        last_ = prev;
        return middle.value_;
      }
      if (prev == null) {
        next.prev_ = null;
        first_ = next;
        return middle.value_;
      }
      prev.next_ = next;
      next.prev_ = prev;
      return middle.value_;
    }
    return null;
  };

  public Transaction[] toArray() {
    Transaction[] array = new Transaction[size_];
    Node middle = first_;
    for (int i = 0; i < size_ && middle != null; ++i) {
      array[i] = middle.value_;
      middle = middle.next_;
    }
    return array;
  };

  public void printTransactionsList() {
    Node middle = first_;
    while (middle != null && size_ != 0) {
      String sender = middle.value_.getSender().getName();
      int id = middle.value_.getSender().getId();
      System.out.println("To " + sender + "(id = " + id + ") " + middle.value_.getTransferAmount()
          + " with id = " + middle.value_.getId());
      middle = middle.next_;
    }
  }
}
