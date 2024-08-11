package ex02_ProducerConsumerModel;

public class Egg extends Thread {
  private int count;

  public Egg(int newCount) {
    count = newCount;
  }

  @Override
  public void run() {
    for (int i = 0; i < count; ++i) {
      Producer.putEggInQueue();
    }
  }
}
