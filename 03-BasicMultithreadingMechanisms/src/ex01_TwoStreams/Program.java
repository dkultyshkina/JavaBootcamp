package ex01_TwoStreams;

import java.util.Arrays;

public class Program {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.exit(0);
    }
    String commandLine = Arrays.toString(args);
    String[] line = commandLine.split("=");
    if (!commandLine.isEmpty() && "[--count".equals(line[0])) {
      String count_str = line[1].replace("]", "");
      int count = Integer.valueOf(count_str);
      if (count <= 0) {
        System.exit(0);
      }
      Egg egg = new Egg(count);
      Hen hen = new Hen(count);
      egg.start();
      hen.start();
      try {
        egg.join();
        hen.join();
      } catch (InterruptedException e) {
        System.out.println(e.toString());
      }
      for (int i = 0; i < count; ++i) {
        System.out.println("Human");
      }
    }
  }
}
