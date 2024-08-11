package edu.game.entity;

public class Wall implements Entity {
  private char symbolWall;
  private String colorWall;

  public Wall() {
    symbolWall = '#';
    colorWall = "GREEN";
  }

  public Wall(char newSymbol, String newColor) {
    symbolWall = newSymbol;
    colorWall = newColor.toUpperCase();
  }

  public char getSymbol() {
    return symbolWall;
  }

  public String getColor() {
    return colorWall;
  }
  public void setSymbol(char newSymbol) {
    symbolWall = newSymbol;
  }
  public void setColor(String newColor) {
    colorWall = newColor.toUpperCase();
  }
}
