package java_.printer.app;

import java_.printer.logic.*;

public class Program {
  static char blackChar;
  static char whiteChar;

  public static void main(String[] args) {
    if (args.length < 2 || args.length > 3) {
      System.exit(0);
    }
    blackChar = args[0].charAt(0);
    whiteChar = args[1].charAt(0);
    processCommandLine();
  }

  private static void processCommandLine() {
    Printer printer = new Printer(blackChar, whiteChar);
    printer.printFilesFromDirectory();
  }
}
