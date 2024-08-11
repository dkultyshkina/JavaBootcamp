package ex06_Menu;

import java.util.Arrays;

public class Program {
  public static void main(String[] args) {
    String commandLine = Arrays.toString(args);
    if ("[--profile=dev]".equals(commandLine)) {
      Menu menu = new Menu(true);
      menu.proccessMenu();
    } else {
      Menu menu = new Menu(false);
      menu.proccessMenu();
    }
  }
}
