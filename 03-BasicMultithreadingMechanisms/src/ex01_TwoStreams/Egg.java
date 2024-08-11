package ex01_TwoStreams;

public class Egg extends Thread {
  private int count;

  public Egg(int newCount) {
    count = newCount;
  }

  @Override
  public void run() {
    for (int i = 0; i < count; ++i) {
      System.out.println("Egg");
    }
  }
}
