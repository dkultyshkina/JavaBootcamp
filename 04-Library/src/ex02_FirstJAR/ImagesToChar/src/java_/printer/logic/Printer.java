package java_.printer.logic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import javax.imageio.ImageIO;

public class Printer {
  private Path DIRECTORY_FILE = Paths.get(
      new File(".").getAbsoluteFile().getParentFile().getAbsoluteFile() + "/target/resources");
  private char blackChar;
  private char whiteChar;

  public Printer(char newBlackChar, char newWhiteChar) {
    blackChar = newBlackChar;
    whiteChar = newWhiteChar;
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
      BufferedImage image = ImageIO.read(fileName.toFile());
      int width = image.getWidth();
      int height = image.getHeight();
      for (int i = 0; i < height; ++i) {
        for (int j = 0; j < width; ++j) {
          int pixel = image.getRGB(j, i);
          if (pixel == Color.WHITE.getRGB()) {
            System.out.print(whiteChar);
          } else {
            System.out.print(blackChar);
          }
        }
        System.out.println();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
