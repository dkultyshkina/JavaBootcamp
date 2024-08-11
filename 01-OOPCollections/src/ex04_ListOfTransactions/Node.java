package ex04_ListOfTransactions;

public class Node {
  public Transaction value_;
  public Node prev_;
  public Node next_;

  Node(Transaction newValue, Node newPrev, Node newNext) {
    value_ = newValue;
    prev_ = newPrev;
    next_ = newNext;
  }
}
