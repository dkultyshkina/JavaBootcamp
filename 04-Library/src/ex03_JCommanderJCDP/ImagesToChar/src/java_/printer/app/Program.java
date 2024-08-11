package java_.printer.app;

import com.beust.jcommander.*;
import java_.printer.logic.*;

public class Program {
  static String blackColour;
  static String whiteColour;

  public static void main(String[] args) {
    ParametersArgs jArgs = new ParametersArgs();
    JCommander commandLine = JCommander.newBuilder().addObject(jArgs).build();
    commandLine.parse(args);
    whiteColour = jArgs.getWhite();
    blackColour = jArgs.getBlack();
    processCommandLine();
  }

  private static void processCommandLine() {
    Printer printer = new Printer(blackColour, whiteColour);
    printer.printFilesFromDirectory();
  }
}
