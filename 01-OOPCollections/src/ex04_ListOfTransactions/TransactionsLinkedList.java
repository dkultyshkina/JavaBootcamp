package ex04_ListOfTransactions;

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

  public void removeTransaction(UUID id) {
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
        return;
      }
      if (prev == null) {
        next.prev_ = null;
        return;
      }
      prev.next_ = next;
      next.prev_ = prev;
    }
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
    while (middle != null) {
      System.out.println(middle.value_.getSender().getName() + " -> "
          + middle.value_.getRecipient().getName() + ", " + middle.value_.getTransferAmount() + ", "
          + middle.value_.getName() + ", " + middle.value_.getId());
      middle = middle.next_;
    }
  }
}
