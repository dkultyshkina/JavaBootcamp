package edu.game.entity;

public class TemplateProperties {
  private final String ENEMY_CHAR = "enemy.char";
  private final String PLAYER_CHAR = "player.char";
  private final String WALL_CHAR = "wall.char";
  private final String GOAL_CHAR = "goal.char";
  private final String EMPTY_CHAR = "empty.char";

  private final String ENEMY_COLOR = "enemy.color";
  private final String PLAYER_COLOR = "player.color";
  private final String WALL_COLOR = "wall.color";
  private final String GOAL_COLOR = "goal.color";
  private final String EMPTY_COLOR = "empty.color";

  public int checkTemplateChar(String differentStr) {
    if (checkEmptyChar(differentStr)) {
      return 1;
    }
    if (checkEnemyChar(differentStr)) {
      return 2;
    }
    if (checkGoalChar(differentStr)) {
      return 3;
    }
    if (checkPlayerChar(differentStr)) {
      return 4;
    }
    if (checkWallChar(differentStr)) {
      return 5;
    }
    return 0;
  }

  public int checkTemplateColor(String differentStr) {
    if (checkEmptyColor(differentStr)) {
      return 1;
    }
    if (checkEnemyColor(differentStr)) {
      return 2;
    }
    if (checkGoalColor(differentStr)) {
      return 3;
    }
    if (checkPlayerColor(differentStr)) {
      return 4;
    }
    if (checkWallColor(differentStr)) {
      return 5;
    }
    return 0;
  }

  public boolean checkPlayerChar(String differentStr) {
    return PLAYER_CHAR.equals(differentStr);
  }
  public boolean checkPlayerColor(String differentStr) {
    return PLAYER_COLOR.equals(differentStr);
  }

  public boolean checkEnemyChar(String differentStr) {
    return ENEMY_CHAR.equals(differentStr);
  }
  public boolean checkEnemyColor(String differentStr) {
    return ENEMY_COLOR.equals(differentStr);
  }

  public boolean checkWallChar(String differentStr) {
    return WALL_CHAR.equals(differentStr);
  }
  public boolean checkWallColor(String differentStr) {
    return WALL_COLOR.equals(differentStr);
  }

  public boolean checkGoalChar(String differentStr) {
    return GOAL_CHAR.equals(differentStr);
  }
  public boolean checkGoalColor(String differentStr) {
    return GOAL_COLOR.equals(differentStr);
  }

  public boolean checkEmptyChar(String differentStr) {
    return EMPTY_CHAR.equals(differentStr);
  }
  public boolean checkEmptyColor(String differentStr) {
    return EMPTY_COLOR.equals(differentStr);
  }
}
