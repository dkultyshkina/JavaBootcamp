package java_.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import javax.imageio.ImageIO;

public class Printer {
  private Path DIRECTORY_FILE = Paths.get(
      new File(".").getAbsoluteFile().getParentFile().getAbsoluteFile() + "/target/resources");
  private String blackColour;
  private String whiteColour;

  public Printer(String newBlackColour, String newWhiteColour) {
    blackColour = newBlackColour.toUpperCase();
    whiteColour = newWhiteColour.toUpperCase();
  };

  public void printFilesFromDirectory() {
    try {
      DirectoryStream<Path> stream = Files.newDirectoryStream(DIRECTORY_FILE);
      for (Path file : stream) {
        if (file.toFile().isFile()) {
          printImage(file);
        }
      }
      stream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void printImage(Path fileName) {
    try {
      if (!checkColor()) {
        return;
      }
      ColoredPrinter printer = new ColoredPrinter();
      BufferedImage image = ImageIO.read(fileName.toFile());
      int width = image.getWidth();
      int height = image.getHeight();
      for (int i = 0; i < height; ++i) {
        for (int j = 0; j < width; ++j) {
          int pixel = image.getRGB(j, i);
          if (pixel == Color.WHITE.getRGB()) {
            printer.print(
                "  ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(whiteColour));
          } else {
            printer.print(
                "  ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(blackColour));
          }
        }
        System.out.println();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean checkColor() {
    try {
      Ansi.BColor.valueOf(blackColour);
      Ansi.BColor.valueOf(whiteColour);
      return true;
    } catch (IllegalArgumentException e) {
      System.out.println("Enter the correct parameters!");
      return false;
    }
  }
}
