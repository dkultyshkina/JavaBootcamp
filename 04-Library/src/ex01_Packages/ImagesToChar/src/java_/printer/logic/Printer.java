package java_.printer.logic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Printer {
  private String fileName;
  private char blackChar;
  private char whiteChar;

  public Printer(String newFileName, char newBlackChar, char newWhiteChar) {
    fileName = newFileName;
    blackChar = newBlackChar;
    whiteChar = newWhiteChar;
  };

  public void printFromFile() {
    try {
      BufferedImage image = ImageIO.read(new File(fileName));
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
      System.out.println(e.toString());
    }
  }
}
