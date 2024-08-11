package edu.game.logic;
import chase.logic.Logic;
import edu.game.entity.GameMap;
import edu.game.entity.Properties;
import java.util.HashMap;
import java.util.Scanner;

public class GameProcess {
  private static Properties properties;
  private static String enemiesCount;
  private static String wallsCount;
  private static String size;
  private static String profile;

  public GameProcess(
      String newEnemiesCount, String newWallsCount, String newSize, String newProfile) {
    enemiesCount = newEnemiesCount;
    wallsCount = newWallsCount;
    size = newSize;
    profile = newProfile;
  }

  public void startGameProcess() {
    if (profile.equals("production")) {
      processProduction();
    } else if (profile.equals("dev")) {
      processDev();
    }
    System.out.println("The game is over");
  }

  private static void processProperties(String fileName) {
    ReaderProperties reader = new ReaderProperties(fileName);
    reader.readResources();
    HashMap<String, String> data = reader.getDataField();
    ParserProperties parser = new ParserProperties(data);
    parser.parseInformationProperties();
    properties = parser.getProperties();
  }

  private static void processProduction() {
    processProperties("application-production.properties");
    GameMap gameMap = new GameMap(enemiesCount, wallsCount, size, properties);
    MapLogic mapLogic = new MapLogic(gameMap);
    mapLogic.fillMap();
    mapLogic.fillPositionsOfEntities();
    processGame(mapLogic, gameMap);
  }

  private static void processDev() {
    processProperties("application-dev.properties");
    GameMap gameMap = new GameMap(enemiesCount, wallsCount, size, properties);
    MapLogic mapLogic = new MapLogic(gameMap);
    mapLogic.fillMap();
    mapLogic.fillPositionsOfEntities();
    processGame(mapLogic, gameMap);
  }

  private static void processGame(MapLogic mapLogic, GameMap gameMap) {
    while (true) {
      PrinterMap printer = new PrinterMap(mapLogic.getGameMap());
      printer.printMap();
      Scanner scanner = new Scanner(System.in);
      char inputChar = scanner.next().charAt(0);
      if (inputChar == '9') {
        break;
      }
      Logic logic = new Logic(gameMap.getPositionsOfEntities(), gameMap.getMap(),
          Integer.parseInt(enemiesCount), gameMap.getProperties().getWall().getSymbol(),
          gameMap.getProperties().getGoal().getSymbol(),
          gameMap.getProperties().getPlayer().getSymbol(),
          gameMap.getProperties().getEnemy().getSymbol(),
          gameMap.getProperties().getEmpty().getSymbol());
      if ("production".equals(profile)) {
        System.out.print("\033[H\033[2J");
      }
      if (logic.movePlayer(inputChar)) {
        printer.printMap();
        System.out.println("You winner!");
        break;
      }
      if ("production".equals(profile)) {
        System.out.print("\033[H\033[2J");
      }
      printer.printMap();
      if ("dev".equals(profile)) {
        System.out.println("Enemy's step: please, you need to confirm this by entering 8");
        if (scanner.next().charAt(0) != '8') {
          continue;
        }
      }
      if (logic.chasePlayer()) {
        printer.printMap();
        System.out.println("You lose!");
        break;
      }
      if ("production".equals(profile)) {
        System.out.print("\033[H\033[2J");
      }
      printer.printMap();
      mapLogic.fillPositionsOfEntities();
    }
  }
}
