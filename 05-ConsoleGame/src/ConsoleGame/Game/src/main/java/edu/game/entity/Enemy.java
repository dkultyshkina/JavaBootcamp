package edu.game.entity;

public class Enemy implements Entity {
  private char symbolEnemy;
  private String colorEnemy;

  public Enemy() {
    symbolEnemy = '1';
    colorEnemy = "RED";
  }

  public Enemy(char newSymbol, String newColor) {
    symbolEnemy = newSymbol;
    colorEnemy = newColor.toUpperCase();
  }

  public char getSymbol() {
    return symbolEnemy;
  }

  public String getColor() {
    return colorEnemy;
  }

  public void setSymbol(char newSymbol) {
    symbolEnemy = newSymbol;
  }

  public void setColor(String newColor) {
    colorEnemy = newColor.toUpperCase();
  }
}
