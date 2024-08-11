package edu.game.entity;

public class Empty implements Entity {
  private char symbolEmpty;
  private String colorEmpty;

  public Empty() {
    symbolEmpty = '_';
    colorEmpty = "WHITE";
  }

  public Empty(char newSymbol, String newColor) {
    symbolEmpty = newSymbol;
    colorEmpty = newColor.toUpperCase();
  }

  @Override
  public char getSymbol() {
    return symbolEmpty;
  }

  @Override
  public String getColor() {
    return colorEmpty;
  }
  public void setSymbol(char newSymbol) {
    symbolEmpty = newSymbol;
  }
  public void setColor(String newColor) {
    colorEmpty = newColor.toUpperCase();
  }
}
