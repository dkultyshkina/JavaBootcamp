package chase.logic;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Logic {
  private Map<String, List<Point>> map;

  private char[][] gameMap;

  private int enemiesCount;

  private char wallSymbol;

  private char goalSymbol;

  private final char playerSymbol;

  private char enemySymbol;

  private final char emptySymbol;

  public Logic(Map<String, List<Point>> map, char[][] gameMap, int enemiesCount, char wallSymbol,
      char goalSymbol, char playerSymbol, char enemySymbol, char emptySymbol) {
    this.map = map;
    this.gameMap = gameMap;
    this.enemiesCount = enemiesCount;
    this.wallSymbol = wallSymbol;
    this.goalSymbol = goalSymbol;
    this.playerSymbol = playerSymbol;
    this.enemySymbol = enemySymbol;
    this.emptySymbol = emptySymbol;
  }

  public Map<String, List<Point>> getMap() {
    return map;
  }

  public void setMap(Map<String, List<Point>> map) {
    this.map = map;
  }

  public int getEnemiesCount() {
    return enemiesCount;
  }

  public void setEnemiesCount(int enemiesCount) {
    this.enemiesCount = enemiesCount;
  }

  public char getWallSymbol() {
    return wallSymbol;
  }

  public void setWallSymbol(char wallSymbol) {
    this.wallSymbol = wallSymbol;
  }

  public char getGoalSymbol() {
    return goalSymbol;
  }

  public void setGoalSymbol(char goalSymbol) {
    this.goalSymbol = goalSymbol;
  }

  public char getEnemySymbol() {
    return enemySymbol;
  }

  public void setEnemySymbol(char enemySymbol) {
    this.enemySymbol = enemySymbol;
  }

  public boolean chasePlayer() {
    for (int i = 0; i < enemiesCount; i++) {
      Point player = map.get("player").getFirst();
      Point enemy = map.get("enemy").get(i);
      int enemyX = enemy.x;
      int enemyY = enemy.y;
      int playerX = player.x;
      int playerY = player.y;
      boolean wallLeft = gameMap[enemyX][enemyY - 1] == wallSymbol;
      boolean wallRight = gameMap[enemyX][enemyY + 1] == wallSymbol;
      boolean wallUp = gameMap[enemyX - 1][enemyY] == wallSymbol;
      boolean wallDown = gameMap[enemyX + 1][enemyY] == wallSymbol;
      boolean goalLeft = gameMap[enemyX][enemyY - 1] == goalSymbol;
      boolean goalRight = gameMap[enemyX][enemyY + 1] == goalSymbol;
      boolean goalUp = gameMap[enemyX - 1][enemyY] == goalSymbol;
      boolean goalDown = gameMap[enemyX + 1][enemyY] == goalSymbol;
      boolean enemyLeft = gameMap[enemyX][enemyY - 1] == enemySymbol;
      boolean enemyRight = gameMap[enemyX][enemyY + 1] == enemySymbol;
      boolean enemyUp = gameMap[enemyX - 1][enemyY] == enemySymbol;
      boolean enemyDown = gameMap[enemyX + 1][enemyY] == enemySymbol;
      boolean nearPlayer = Math.abs(playerX - enemyX) <= 1 && Math.abs(playerY - enemyY) <= 1;
      if (nearPlayer) {
        return true;
      }
      if (wallLeft || wallRight || wallUp || wallDown || !nearPlayer) {
        if ((enemyX < playerX) && !wallDown && !goalDown && !enemyDown) {
          enemy.x = (enemyX + 1);
          gameMap[enemyX][enemyY] = emptySymbol;
          enemyX++;
        } else if ((enemyX > playerX) && !wallUp && !goalUp && !enemyUp) {
          enemy.x = (enemyX - 1);
          gameMap[enemyX][enemyY] = emptySymbol;
          enemyX--;
        } else if ((enemyY < playerY) && !wallRight && !goalRight && !enemyRight) {
          enemy.y = (enemyY + 1);
          gameMap[enemyX][enemyY] = emptySymbol;
          enemyY++;
        } else if ((enemyY > playerY) && !wallLeft && !goalLeft && !enemyLeft) {
          enemy.y = (enemyY - 1);
          gameMap[enemyX][enemyY] = emptySymbol;
          enemyY--;
        }
        gameMap[enemyX][enemyY] = enemySymbol;
      }
    }
    return false;
  }

  public boolean movePlayer(char key) {
    int x = map.get("player").getFirst().x;
    int y = map.get("player").getFirst().y;
    int prevX = x;
    int prevY = y;
    if ((key == 'w') || (key == 'W')) {
      if ((gameMap[x - 1][y]) != wallSymbol) {
        x = x - 1;
      } else {
        inputChar();
        return false;
      }
    } else if ((key == 's') || (key == 'S')) {
      if ((gameMap[x + 1][y]) != wallSymbol) {
        x = x + 1;
      } else {
        inputChar();
        return false;
      }
    } else if ((key == 'a') || (key == 'A')) {
      if ((gameMap[x][y - 1]) != wallSymbol) {
        y = y - 1;
      } else {
        inputChar();
        return false;
      }
    } else if ((key == 'd') || (key == 'D')) {
      if ((gameMap[x][y + 1]) != wallSymbol) {
        y = y + 1;
      } else {
        inputChar();
        return false;
      }
    } else {
      inputChar();
      return false;
    }
    char symbol = gameMap[x][y];
    if (symbol == wallSymbol) {
      return false;
    } else if (symbol == goalSymbol) {
      return true;
    } else if (symbol == enemySymbol) {
      return false;
    }
    gameMap[prevX][prevY] = emptySymbol;
    gameMap[x][y] = playerSymbol; // поменять местами х и у
    map.get("player").getFirst().x = x;
    map.get("player").getFirst().y = y;
    return false;
  }

  private void inputChar() {
    System.out.println(
        "Enter a number in the console that corresponds to the movement direction A, W, D, S");
    Scanner scanner = new Scanner(System.in);
    char inputChar = scanner.next().charAt(0);
    movePlayer(inputChar);
  }
}
