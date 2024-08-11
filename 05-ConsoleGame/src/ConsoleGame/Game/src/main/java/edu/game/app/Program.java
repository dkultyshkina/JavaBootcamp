package edu.game.app;

import com.beust.jcommander.*;
import edu.game.logic.GameProcess;
import edu.game.logic.ParametersArgs;
import edu.game.logic.*;

public class Program {
  public static void main(String[] args) {
    if (args.length > 0 && args.length < 4) {
      System.exit(0);
    }
    ParametersArgs jArgs = new ParametersArgs();
    JCommander commandLine = JCommander.newBuilder().addObject(jArgs).build();
    commandLine.parse(args);
    GameProcess game = new GameProcess(
        jArgs.getEnemiesCount(), jArgs.getWallsCount(), jArgs.getSize(), jArgs.getProfile());
    game.startGameProcess();
  }
}
