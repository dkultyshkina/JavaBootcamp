package edu.game.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class ParametersArgs {
  @Parameter(names = "--enemiesCount", required = true) private String enemiesCount;

  @Parameter(names = "--wallsCount", required = true) private String wallsCount;

  @Parameter(names = "--size", required = true) private String size;

  @Parameter(names = "--profile", required = true) private String profile;

  public String getEnemiesCount() {
    return enemiesCount;
  }

  public String getWallsCount() {
    return wallsCount;
  }

  public String getSize() {
    return size;
  }

  public String getProfile() {
    return profile;
  }
}
