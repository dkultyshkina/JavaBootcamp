package edu.game.logic;

import edu.game.entity.*;
import edu.game.entity.*;
import edu.game.exception.IllegalColorException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ParserProperties {
  private final HashMap<String, String> dataFieldFromFile;
  private Properties properties;
  private CheckerProperties checker;
  private final TemplateProperties template;

  public ParserProperties(HashMap<String, String> map) {
    dataFieldFromFile = map;
    properties = null;
    checker = null;
    template = new TemplateProperties();
  }

  public Properties getProperties() {
    return properties;
  }

  public void parseInformationProperties() {
    checkWithTemplateProperties();
    checker = new CheckerProperties(properties);
    try {
      checker.checkInformationFromProperties();
    } catch (IllegalColorException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  private void checkWithTemplateProperties() {
    Enemy enemy = new Enemy();
    Empty empty = new Empty();
    Goal goal = new Goal();
    Player player = new Player();
    Wall wall = new Wall();
    Set<HashMap.Entry<String, String>> set = dataFieldFromFile.entrySet();
    for (Map.Entry<String, String> entry : set) {
      switch (template.checkTemplateChar(entry.getKey())) {
        case 1:
          empty.setSymbol(entry.getValue().charAt(0));
          break;
        case 2:
          enemy.setSymbol(entry.getValue().charAt(0));
          break;
        case 3:
          goal.setSymbol(entry.getValue().charAt(0));
          break;
        case 4:
          player.setSymbol(entry.getValue().charAt(0));
          break;
        case 5:
          wall.setSymbol(entry.getValue().charAt(0));
          break;
      }

      switch (template.checkTemplateColor(entry.getKey())) {
        case 1:
          empty.setColor(entry.getValue());
          break;
        case 2:
          enemy.setColor(entry.getValue());
          break;
        case 3:
          goal.setColor(entry.getValue());
          break;
        case 4:
          player.setColor(entry.getValue());
          break;
        case 5:
          wall.setColor(entry.getValue());
          break;
      }
    }
    properties = new Properties(empty, enemy, goal, player, wall);
  }
}
