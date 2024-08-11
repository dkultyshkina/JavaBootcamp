package ex01_TwoStreams;

public class Hen extends Thread {
  private int count;

  public Hen(int newCount) {
    count = newCount;
  }

  @Override
  public void run() {
    for (int i = 0; i < count; ++i) {
      System.out.println("Hen");
    }
  }
}
