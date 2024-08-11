package ex06_Schedule;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextLine()) {
      String input = scanner.nextLine();
      createTimetable(scanner, input);
    }
    scanner.close();
  }

  private static boolean checkPoint(String input) {
    if (!input.isEmpty() && ".".equals(input)) {
      return true;
    }
    return false;
  }

  private static void createTimetable(Scanner scanner, String input) {
    String name[] = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    String timetable[] = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    String attendance[] = new String[100];
    Arrays.fill(attendance, "0");
    int maxName = input.length();
    int i = 0;
    if (!checkPoint(input) && maxName <= 10 && i < 10) {
      name[i] = input;
      ++i;
    }
    while (!checkPoint(input) && i < 10) {
      input = scanner.nextLine();
      name[i] = input;
      if ((name[i].length() <= 10) && (!checkPoint(input))) {
        maxName = name[i].length();
        ++i;
      }
    }
    name[i] = "0";
    i = 0;
    input = scanner.nextLine();
    while (!checkPoint(input) && i < 20) {
      timetable[i] = input;
      String[] line = timetable[i].split(" ");
      int number = Integer.parseInt(line[0]);
      if (number > 0 && number < 7) {
        ++i;
      } else {
        timetable[i] = "0";
      }
      input = scanner.nextLine();
    }
    i = 0;
    input = scanner.nextLine();
    while (!checkPoint(input) && i < 100) {
      attendance[i] = input;
      ++i;
      input = scanner.nextLine();
    }
    printTimetable(name, timetable, attendance);
  }

  private static void printTimetable(String[] name, String[] timetable, String[] attendance) {
    String date[][] = {{"MO", "TU", "WE", "TH", "FR", "SA", "SU"},
        {"0", "1", "2", "3", "4", "5", "6"}, {"7", "8", "9", "10", "11", "12", "13"},
        {"14", "15", "16", "17", "18", "19", "20"}, {"21", "22", "23", "24", "25", "26", "27"},
        {"28", "29", "30", "0", "0", "0", "0"}};
    System.out.print("          ");
    String[] head = new String[100];
    Arrays.fill(head, "0");
    int h = 0;
    h = printHead(timetable, date, 1, head, h);
    h = printHead(timetable, date, 2, head, h);
    h = printHead(timetable, date, 3, head, h);
    h = printHead(timetable, date, 4, head, h);
    h = printHead(timetable, date, 5, head, h);
    System.out.println();
    processingNameAndAttendance(name, attendance, head);
  }

  private static int printHead(String[] timetable, String date[][], int m, String head[], int h) {
    for (int i = 0; i < timetable.length; i++) {
      if ("0".equals(timetable[i])) {
        break;
      }
      String[] dayTime = timetable[i].split(" ");
      for (int k = 0; k < 7; ++k) {
        if (date[0][k].equals(dayTime[1])) {
          if (!("0".equals(date[m][k]))) {
            System.out.print(dayTime[0] + ":00 ");
            System.out.print(dayTime[1] + " ");
            System.out.print(date[m][k]);
            System.out.print("|");
            head[h] = String.join(" ", dayTime[0], dayTime[1], date[m][k]);
            h++;
            break;
          }
        }
      }
    }
    return h;
  }

  private static void processingNameAndAttendance(
      String[] name, String[] attendance, String[] head) {
    for (int i = 0; i < name.length; ++i) {
      String[][] data = new String[31][7];
      fillArray(data);
      if ("0".equals(name[i])) {
        break;
      }
      System.out.print(name[i]);
      if (name[i].length() < 10) {
        int diff = 10 - name[i].length();
        for (int s = 0; s < diff; ++s) {
          System.out.print(" ");
        }
      }
      for (int j = 0; j < head.length; ++j) {
        if ("0".equals(head[j])) {
          break;
        }
        String[] line = head[j].split(" ");
        for (int k = 0; k < attendance.length; ++k) {
          if ("0".equals(attendance[k])) {
            break;
          }
          String[] att = attendance[k].split(" ");
          if (!(name[i].equals(att[0]))) {
            continue;
          }
          int firstIndex = Integer.parseInt(line[2]);
          int secondIndex = Integer.parseInt(line[0]);
          if (line[0].equals(att[1]) && line[2].equals(att[2])) {
            if ("NOT_HERE".equals(att[3])) {
              data[firstIndex][secondIndex] = "-1";
            } else if ("HERE".equals(att[3])) {
              data[firstIndex][secondIndex] = "1";
            }
          }
        }
      }
      printNameAndAttendance(head, data);
      System.out.println();
    }
  }

  private static void fillArray(String[][] array) {
    for (int i = 0; i < 31; ++i) {
      for (int j = 0; j < 7; ++j) {
        array[i][j] = "0";
      }
    }
  }

  private static void printNameAndAttendance(String[] head, String[][] data) {
    for (int i = 0; i < head.length; ++i) {
      if ("0".equals(head[i])) {
        break;
      }
      String[] line = head[i].split(" ");
      int secondIndex = Integer.parseInt(line[0]);
      int number = Integer.parseInt(line[2]);
      for (int j = 0; j < data.length; ++j) {
        if (j == number) {
          if (number > 9) {
            System.out.print(" ");
          }
          if ("0".equals(data[j][secondIndex])) {
            System.out.print("         |");
          } else if ("1".equals(data[j][secondIndex])) {
            System.out.print("        1|");
          } else {
            System.out.print("       -1|");
          }
          break;
        }
      }
    }
  }
}
