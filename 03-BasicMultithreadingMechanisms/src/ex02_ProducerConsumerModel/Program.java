package ex02_ProducerConsumerModel;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Program {
  static private BlockingQueue<String> queue = new SynchronousQueue<String>();
  static private int count;

  public static void main(String[] args) {
    if (args.length == 0) {
      System.exit(0);
    }
    String commandLine = Arrays.toString(args);
    String[] line = commandLine.split("=");
    if (!commandLine.isEmpty() && "[--count".equals(line[0])) {
      String count_str = line[1].replace("]", "");
      count = Integer.valueOf(count_str);
      if (count <= 0) {
        System.exit(0);
      }
      Producer hen = new Producer(queue, count);
      Thread producerThread = new Thread(hen);
      Consumer consumer = new Consumer(queue);
      Thread consumerThread = new Thread(consumer);
      producerThread.start();
      consumerThread.start();
    }
  }
}
