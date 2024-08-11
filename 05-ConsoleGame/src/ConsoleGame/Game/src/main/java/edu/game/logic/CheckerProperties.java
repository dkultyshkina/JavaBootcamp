package edu.game.logic;

import com.diogonunes.jcdp.color.api.Ansi;
import edu.game.entity.Properties;
import edu.game.exception.IllegalColorException;
import edu.game.entity.*;
import edu.game.exception.*;

public class CheckerProperties {
  private final Properties properties;

  public CheckerProperties(Properties newProperties) {
    properties = newProperties;
  }

  public void checkInformationFromProperties() {
    if (!checkColors()) {
      throw new IllegalColorException(
          "\nPlease, enter the correct color data in the properties!\n");
    }
  }

  private boolean checkColors() {
    try {
      Ansi.BColor.valueOf(properties.getEmpty().getColor());
      Ansi.BColor.valueOf(properties.getEnemy().getColor());
      Ansi.BColor.valueOf(properties.getGoal().getColor());
      Ansi.BColor.valueOf(properties.getPlayer().getColor());
      Ansi.BColor.valueOf(properties.getWall().getColor());
      return true;
    } catch (IllegalArgumentException e) {
      System.out.println("Enter the correct parameters!");
      System.exit(1);
      return false;
    }
  }
}
