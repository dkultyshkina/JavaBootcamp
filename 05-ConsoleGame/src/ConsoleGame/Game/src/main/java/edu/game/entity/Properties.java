package edu.game.entity;

public class Properties {
  private Empty empty;
  private Enemy enemy;
  private Goal goal;
  private Player player;
  private Wall wall;

  public Properties(Empty newEmpty, Enemy newEnemy, Goal newGoal, Player newPlayer, Wall newWall) {
    empty = newEmpty;
    enemy = newEnemy;
    goal = newGoal;
    player = newPlayer;
    wall = newWall;
  }

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

  public void setEmpty(Empty newEmpty) {
    empty = newEmpty;
  }

  public void setEnemy(Enemy newEnemy) {
    enemy = newEnemy;
  }

  public void setGoal(Goal newGoal) {
    goal = newGoal;
  }

  public void setPlayer(Player newPlayer) {
    player = newPlayer;
  }

  public void setWall(Wall newWall) {
    wall = newWall;
  }
}
