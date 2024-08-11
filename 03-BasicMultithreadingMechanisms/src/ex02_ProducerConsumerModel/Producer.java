package ex02_ProducerConsumerModel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Producer extends Thread {
  private static BlockingQueue<String> queue = new SynchronousQueue<String>();
  private static int count;
  private static boolean context = false;

  public Producer(BlockingQueue<String> newQueue, int newCount) {
    queue = newQueue;
    count = newCount;
  }

  @Override
  public void run() {
    Egg egg = new Egg(count);
    Hen hen = new Hen(count);
    egg.start();
    hen.start();
  }

  public static synchronized void putHenInQueue() {
    if (!context) {
      try {
        Producer.class.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
        return;
      }
    }
    putNameInQueue("Hen");
    context = false;
    Producer.class.notifyAll();
  }

  public static synchronized void putEggInQueue() {
    if (context) {
      try {
        Producer.class.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
        return;
      }
    }
    putNameInQueue("Egg");
    context = true;
    Producer.class.notifyAll();
  }

  public static void putNameInQueue(String name) {
    try {
      queue.put(name);
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
      return;
    }
  }
}
