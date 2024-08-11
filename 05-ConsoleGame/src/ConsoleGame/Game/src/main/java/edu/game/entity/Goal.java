package edu.game.entity;

public class Goal implements Entity {
  private char symbolGoal;
  private String colorGoal;

  public Goal() {
    symbolGoal = 'O';
    colorGoal = "BLUE";
  }

  public Goal(char newSymbol, String newColor) {
    symbolGoal = newSymbol;
    colorGoal = newColor.toUpperCase();
  }

  public char getSymbol() {
    return symbolGoal;
  }

  public String getColor() {
    return colorGoal;
  }

  public void setSymbol(char newSymbol) {
    symbolGoal = newSymbol;
  }
  public void setColor(String newColor) {
    colorGoal = newColor.toUpperCase();
  }
}
