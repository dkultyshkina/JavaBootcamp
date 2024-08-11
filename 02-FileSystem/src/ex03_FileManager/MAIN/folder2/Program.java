package ex02.MAIN.folder2;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        String commandLine = Arrays.toString(args);
        String[] line = commandLine.split("=");
        if (!commandLine.isEmpty() && "[--current-folder".equals(line[0])) {
            readConsole(line[1]);
        }
    }

    private static void readConsole(String directory) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
              System.exit(0);
            }
        }
        scanner.close();
    }
}
