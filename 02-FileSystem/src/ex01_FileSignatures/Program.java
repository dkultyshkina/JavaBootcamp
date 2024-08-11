package ex01_FileSignatures;

import java.util.HashMap;
import java.util.Scanner;

class Program {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    SignaturesReader signaturesReader = new SignaturesReader();
    HashMap<String, String> mapSignatures = new HashMap<>();
    mapSignatures = signaturesReader.readSignature();
    while (scanner.hasNextLine()) {
      String input = scanner.nextLine();
      if ("42".equals(input)) {
        System.exit(0);
      }
      FinderFileType finder = new FinderFileType(mapSignatures, input);
      String fileType = finder.findFileType();
      if (fileType.isEmpty()) {
        continue;
      }
      FileWriter writer = new FileWriter(fileType);
      writer.writeFileType();
    }
    scanner.close();
  }
}