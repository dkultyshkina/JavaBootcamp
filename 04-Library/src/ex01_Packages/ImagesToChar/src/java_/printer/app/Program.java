package java_.printer.app;

import java_.printer.logic.Printer;

public class Program {
  static char blackChar;
  static char whiteChar;
  static String pathImage;

  public static void main(String[] args) {
    if (args.length < 3) {
      System.exit(0);
    }
    blackChar = args[0].charAt(0);
    whiteChar = args[1].charAt(0);
    pathImage = args[2];
    processCommandLine();
  }

  private static void processCommandLine() {
    Printer printer = new Printer(pathImage, blackChar, whiteChar);
    printer.printFromFile();
  }
}
