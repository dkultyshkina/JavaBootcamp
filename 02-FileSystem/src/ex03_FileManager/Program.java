package ex03_FileManager;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.exit(0);
    }
    String commandLine = Arrays.toString(args);
    String[] line = commandLine.split("=");
    if (!commandLine.isEmpty() && "[--current-folder".equals(line[0])) {
      readConsole(line[1].toString());
    }
  }

  private static void readConsole(String directory) {
    Scanner scanner = new Scanner(System.in);
    CommandLine line = new CommandLine(directory.replace("]", ""));
    while (scanner.hasNextLine()) {
      String input = scanner.nextLine();
      if ("exit".equals(input)) {
        System.exit(0);
      }
      if ("ls".equals(input)) {
        line.runLs();
        continue;
      }
      String[] array = input.split(" ");
      if (array.length >= 2) {
        if ("cd".equals(array[0])) {
          line.runCd(array[1]);
          continue;
        }
        if (array.length == 3 && "mv".equals(array[0])) {
          line.runMv(array[1], array[2]);
          continue;
        }
      }
      System.out.println("command not found: " + input);
    }
    scanner.close();
  }
}
