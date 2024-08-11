package edu.game.entity;

public class Player implements Entity {
  private char symbolPlayer;
  private String colorPlayer;

  public Player() {
    symbolPlayer = '@';
    colorPlayer = "YELLOW";
  }
  public Player(char newSymbol, String newColor) {
    symbolPlayer = newSymbol;
    colorPlayer = newColor.toUpperCase();
  }

  public char getSymbol() {
    return symbolPlayer;
  }

  public String getColor() {
    return colorPlayer;
  }

  public void setSymbol(char newSymbol) {
    symbolPlayer = newSymbol;
  }

  public void setColor(String newColor) {
    colorPlayer = newColor.toUpperCase();
  }

  public static class Field {
    private Empty empty;
    private Enemy enemy;
    private Goal goal;
    private Player player;
    private Wall wall;

    public Empty getEmpty() {
      return empty;
    }

    public Enemy getEnemy() {
      return enemy;
    }

    public Goal getGoal() {
      return goal;
    }

    public Player getPlayer() {
      return player;
    }

    public Wall getWall() {
      return wall;
    }
  }
}
