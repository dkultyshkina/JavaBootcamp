package ex02_Words;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Program {
  public static void main(String[] args) {
    ArrayList<String> firstList = new ArrayList<String>();
    ArrayList<String> secondList = new ArrayList<String>();
    ArrayList<String> dictionary = new ArrayList<String>();
    Vector<Integer> firstVector = new Vector<>();
    Vector<Integer> secondVector = new Vector<>();
    try {
      if (args.length == 0) {
        System.exit(0);
      }
      firstList = readFile(args[0].toString());
      secondList = readFile(args[1].toString());
      dictionary = createDictionary(firstList, secondList);
      writeDictionary(dictionary);
      firstVector = createVector(dictionary, firstList);
      secondVector = createVector(dictionary, secondList);
      double result = countSimilarity(firstVector, secondVector);
      System.out.println("Similarity = " + (Math.floor(result * 100.0) / 100.0));
    } catch (IOException e) {
      e.getStackTrace();
    }
  }

  private static ArrayList<String> readFile(String file) throws IOException {
    FileInputStream fileFirst = new FileInputStream(
        new File(".").getAbsoluteFile().getParentFile().getAbsolutePath() + "/src/ex02_Words/" + file);
    BufferedInputStream bufferedStream = new BufferedInputStream(fileFirst);
    int i = 0;
    ArrayList<String> list = new ArrayList<String>();
    String line = "";
    while ((i = bufferedStream.read()) != -1) {
      if ((char) i == '\n' || (char) i == ' ') {
        list.add(line);
        line = "";
        continue;
      }
      line += (char) i;
    }
    list.add(line);
    bufferedStream.close();
    fileFirst.close();
    return list;
  }

  private static ArrayList<String> createDictionary(
      ArrayList<String> firstList, ArrayList<String> secondList) {
    ArrayList<String> dictionary = new ArrayList<>();
    for (String line : firstList) {
      if (!findWord(dictionary, line)) {
        dictionary.add(line);
      }
    }
    for (String line : secondList) {
      if (!findWord(dictionary, line)) {
        dictionary.add(line);
      }
    }
    Collections.sort(dictionary);
    return dictionary;
  }

  private static boolean findWord(ArrayList<String> list, String word) {
    if (list.isEmpty()) {
      return false;
    }
    for (String line : list) {
      if (line.equals(word)) {
        return true;
      }
    }
    return false;
  }

  private static void writeDictionary(ArrayList<String> dictionary) {
    try {
      FileOutputStream output =
          new FileOutputStream(new File(".").getAbsoluteFile().getParentFile().getAbsolutePath()
              + "/src/ex02_Words/dictionary.txt");

      for (String line : dictionary) {
        byte[] array = (line + " ").getBytes();
        output.write(array);
      }
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static Vector<Integer> createVector(
      ArrayList<String> dictionary, ArrayList<String> list) {
    Vector<Integer> vector = new Vector<>();
    int count = 0;
    for (String line : dictionary) {
      count = countWord(list, line);
      vector.add(count);
    }
    return vector;
  }

  private static int countWord(ArrayList<String> list, String word) {
    if (list.isEmpty()) {
      return 0;
    }
    int count = 0;
    for (String line : list) {
      if (line.equals(word)) {
        ++count;
      }
    }
    return count;
  }

  private static double countSimilarity(Vector<Integer> firstVector, Vector<Integer> secondVector) {
    Integer numerator = 0;
    if (firstVector.size() != secondVector.size()) {
      System.exit(0);
    }
    for (int i = 0; i < firstVector.size(); ++i) {
      numerator += (firstVector.get(i) * secondVector.get(i));
    }
    double firstDenominator = 0, secondDenominator = 0, denominator = 0;
    for (int i : firstVector) {
      firstDenominator += i * i;
    }
    for (int i : secondVector) {
      secondDenominator += i * i;
    }
    denominator = Math.sqrt(firstDenominator) * Math.sqrt(secondDenominator);
    return numerator / denominator;
  }
}
