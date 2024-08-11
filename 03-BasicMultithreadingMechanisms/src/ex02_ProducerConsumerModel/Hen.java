package ex02_ProducerConsumerModel;

public class Hen extends Thread {
  private int count;

  public Hen(int newCount) {
    count = newCount;
  }

  @Override
  public void run() {
    for (int i = 0; i < count; ++i) {
      Producer.putHenInQueue();
    }
  }
}
