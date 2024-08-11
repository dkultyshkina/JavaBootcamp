package edu.game.entity;

import edu.game.exception.IllegalParametersException;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {
  private int width;
  private int height;
  private int enemiesCount;
  private int wallsCount;
  private char[][] map;
  private Properties properties;

  private Map<String, List<Point>> positionsOfEntities = new HashMap<>();

  public GameMap(String enemiesCount, String wallsCount, String size, Properties properties) {
    this.enemiesCount = Integer.parseInt(enemiesCount);
    this.wallsCount = Integer.parseInt(wallsCount);
    this.width = Integer.parseInt(size) + 2;
    this.height = Integer.parseInt(size) + 2;
    this.map = new char[width][height];
    this.properties = properties;
    checkLegalParameters();
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getEnemiesCount() {
    return enemiesCount;
  }

  public void setEnemiesCount(int enemiesCount) {
    this.enemiesCount = enemiesCount;
  }

  public int getWallsCount() {
    return wallsCount;
  }

  public void setWallsCount(int wallsCount) {
    this.wallsCount = wallsCount;
  }

  public char[][] getMap() {
    return map;
  }

  public void setMap(char[][] map) {
    this.map = map;
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public Map<String, List<Point>> getPositionsOfEntities() {
    return positionsOfEntities;
  }

  public void setPositionsOfEntities(Map<String, List<Point>> positionsOfEntities) {
    this.positionsOfEntities = positionsOfEntities;
  }

  private void checkLegalParameters() {
    try {
      if ((enemiesCount + wallsCount + 2) > ((height - 2) * (width - 2))) {
        throw new IllegalParametersException("Sorry, you entered incorrect data in the parameters");
      }
    } catch (IllegalParametersException e) {
      System.out.println(e.toString());
      System.exit(1);
    }
  }
}
