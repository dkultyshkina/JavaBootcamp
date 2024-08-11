package edu.game.logic;

import edu.game.entity.GameMap;
import edu.game.entity.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapLogic {
  private GameMap gameMap;

  public MapLogic(GameMap gameMap) {
    this.gameMap = gameMap;
  }

  public GameMap getGameMap() {
    return gameMap;
  }

  public void fillMap() {
    for (int i = 0; i < gameMap.getWidth(); i++) {
      for (int j = 0; j < gameMap.getHeight(); j++) {
        if (i == 0 || j == 0 || i == gameMap.getWidth() - 1 || j == gameMap.getHeight() - 1) {
          gameMap.getMap()[i][j] = gameMap.getProperties().getWall().getSymbol();
        } else {
          gameMap.getMap()[i][j] = gameMap.getProperties().getEmpty().getSymbol();
        }
      }
    }
    int[] partsMap = new int[3];
    int min = 1;
    int max = gameMap.getWidth() - 2; // до 3 нужно наверное
    partsMap[0] = gameMap.getHeight() / 3;
    partsMap[1] = gameMap.getHeight() / 3;
    partsMap[2] = gameMap.getHeight() - 2 - partsMap[0] - partsMap[1]; // 3
    int randomX = min + (int) (Math.random() * max);
    int randomY = min + (int) (Math.random() * partsMap[0]);
    gameMap.getMap()[randomY][randomX] = gameMap.getProperties().getGoal().getSymbol();
    randomFill(min, max, 1, partsMap[0], gameMap.getEnemiesCount(),
        gameMap.getProperties().getEnemy().getSymbol());
    randomFill(min, max, partsMap[0], partsMap[1] + partsMap[0], gameMap.getWallsCount(),
        gameMap.getProperties().getWall().getSymbol());
    boolean checkPlayer = false;
    while (!checkPlayer) {
      randomX = min + (int) (Math.random() * max);
      randomY = partsMap[0] + partsMap[1] + (int) (Math.random() * partsMap[2]);
      gameMap.getMap()[randomY][randomX] = gameMap.getProperties().getPlayer().getSymbol();
      if (checkPlayerPosition(new Point(randomX, randomY)) == null) {
        checkPlayer = true;
      } else {
        gameMap.getMap()[randomY][randomX] = gameMap.getProperties().getEmpty().getSymbol();
      }
    }
    Optional<List<Point>> positionsOfEnemies = checkPositionsOfEntities("enemy");
    if (positionsOfEnemies.isPresent()) {
      if (positionsOfEnemies.get().size() != gameMap.getEnemiesCount()) {
        randomFill(min, max, 1, partsMap[0], gameMap.getEnemiesCount(),
            gameMap.getProperties().getEnemy().getSymbol());
      }
    }
  }

  public void randomFill(
      int min, int max, int minHeight, int maxHeight, int countIterations, char typeEntityOnMap) {
    int randomX;
    int randomY;
    for (int i = 0; i < countIterations; i++) {
      do {
        randomX = min + (int) (Math.random() * max);
        randomY = minHeight + (int) (Math.random() * maxHeight);
      } while (!checkPosition(randomX, randomY));
      gameMap.getMap()[randomX][randomY] = typeEntityOnMap;
    }
  }

  public boolean checkPosition(int x, int y) {
    return gameMap.getMap()[x][y] == gameMap.getProperties().getEmpty().getSymbol();
  }

  public Point checkPlayerPosition(Point playerPosition) {
    int x = playerPosition.x;
    int y = playerPosition.y;
    if (gameMap.getMap()[y - 1][x] == gameMap.getProperties().getEnemy().getSymbol()) {
      return new Point(y - 1, x);
    } else if (gameMap.getMap()[y + 1][x] == gameMap.getProperties().getEnemy().getSymbol()) {
      return new Point(y + 1, x);
    } else if (gameMap.getMap()[y][x - 1] == gameMap.getProperties().getEnemy().getSymbol()) {
      return new Point(y, x - 1);
    } else if (gameMap.getMap()[y][x + 1] == gameMap.getProperties().getEnemy().getSymbol()) {
      return new Point(y, x + 1);
    }
    return null;
  }

  public void fillPositionsOfEntities() {
    for (int i = 0; i < gameMap.getWidth(); i++) {
      for (int j = 0; j < gameMap.getHeight(); j++) {
        char curSymbol = gameMap.getMap()[i][j];
        if (curSymbol == gameMap.getProperties().getEmpty().getSymbol()) {
          continue;
        } else if (curSymbol == gameMap.getProperties().getWall().getSymbol()) {
          fillMapOfEntities(i, j, "wall");
        } else if (curSymbol == gameMap.getProperties().getEnemy().getSymbol()) {
          fillMapOfEntities(i, j, "enemy");
        } else if (curSymbol == gameMap.getProperties().getGoal().getSymbol()) {
          fillMapOfEntities(i, j, "goal");
        } else if (curSymbol == gameMap.getProperties().getPlayer().getSymbol()) {
          fillMapOfEntities(i, j, "player");
        }
      }
    }
  }

  public Optional<List<Point>> checkPositionsOfEntities(String entity) {
    return Optional.ofNullable(gameMap.getPositionsOfEntities().get(entity));
  }

  public void fillMapOfEntities(int x, int y, String entity) {
    List<Point> pointsList = gameMap.getPositionsOfEntities().get(entity);
    if (pointsList == null) {
      pointsList = new ArrayList<>();
    }
    pointsList.add(new Point(x, y));
    gameMap.getPositionsOfEntities().put(entity, pointsList);
  }
}
