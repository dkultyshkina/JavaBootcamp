package ex02_ProducerConsumerModel;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
  private final BlockingQueue<String> queue;

  public Consumer(BlockingQueue<String> newQueue) {
    queue = newQueue;
  }

  public BlockingQueue<String> getQueue() {
    return queue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        System.out.println(queue.take());
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
        break;
      }
    }
  }
}
