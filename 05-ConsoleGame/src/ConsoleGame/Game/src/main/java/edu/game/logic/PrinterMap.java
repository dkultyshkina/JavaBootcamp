package edu.game.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import edu.game.entity.GameMap;

public class PrinterMap {
  private final GameMap gameMap;
  ColoredPrinter printer;

  public PrinterMap(GameMap newMap) {
    gameMap = newMap;
    printer = new ColoredPrinter();
  }

  public void printMap() {
    for (int i = 0; i < gameMap.getWidth(); i++) {
      for (int j = 0; j < gameMap.getHeight(); j++) {
        printEntities(gameMap.getMap()[i][j]);
      }
      System.out.println();
    }
  }

  private void printEntities(char symbol) {
    if (gameMap.getProperties().getGoal().getSymbol() == symbol) {
      printer.print(gameMap.getProperties().getGoal().getSymbol(), Ansi.Attribute.NONE,
          Ansi.FColor.BLACK, Ansi.BColor.valueOf(gameMap.getProperties().getGoal().getColor()));
    } else if (gameMap.getProperties().getEmpty().getSymbol() == symbol) {
      printer.print(gameMap.getProperties().getEmpty().getSymbol(), Ansi.Attribute.NONE,
          Ansi.FColor.BLACK, Ansi.BColor.valueOf(gameMap.getProperties().getEmpty().getColor()));
    } else if (gameMap.getProperties().getEnemy().getSymbol() == symbol) {
      printer.print(gameMap.getProperties().getEnemy().getSymbol(), Ansi.Attribute.NONE,
          Ansi.FColor.BLACK, Ansi.BColor.valueOf(gameMap.getProperties().getEnemy().getColor()));
    } else if (gameMap.getProperties().getPlayer().getSymbol() == symbol) {
      printer.print(gameMap.getProperties().getPlayer().getSymbol(), Ansi.Attribute.NONE,
          Ansi.FColor.BLACK, Ansi.BColor.valueOf(gameMap.getProperties().getPlayer().getColor()));
    } else if (gameMap.getProperties().getWall().getSymbol() == symbol) {
      printer.print(gameMap.getProperties().getWall().getSymbol(), Ansi.Attribute.NONE,
          Ansi.FColor.BLACK, Ansi.BColor.valueOf(gameMap.getProperties().getWall().getColor()));
    }
  }
}
