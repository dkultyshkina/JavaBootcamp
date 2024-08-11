package ex03_FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class CommandLine {
  Path path_;

  public CommandLine(String nameDirectory) {
    path_ = Paths.get(nameDirectory);
  }

  public void runLs() {
    File file;
    long length = 0;
    String size;
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(path_)) {
      for (Path entry : stream) {
        file = entry.toFile();
        length = file.length();
        size = " B";
        if (length / 1024 != 0) {
          length /= 1024;
          size = " KB";
        }
        System.out.println(entry.getFileName() + " " + length + size);
      }
    } catch (IOException e) {
      System.out.println(e.toString());
    }
  }

  public void runMv(String first, String second) {
    Path firstPath = convertToPath(first);
    Path secondPath = convertToPath(second);
    try {
      if (Files.exists(secondPath) && Files.isDirectory(secondPath)) {
        Files.move(firstPath, secondPath.resolve(firstPath.getFileName()).normalize(),
            StandardCopyOption.REPLACE_EXISTING);
      } else if (Files.exists(firstPath)) {
        Files.move(firstPath, secondPath);
      } else {
        System.out.println("mv: cannot stat " + first + ": No such file or directory");
      }
    } catch (IOException e) {
      System.out.println("mv: cannot stat " + first + ": No such file or directory");
    }
  }

  public void runCd(String directory) {
    Path newPath = convertToPath(directory);
    if (Files.exists(newPath) && Files.isDirectory(newPath)) {
      path_ = newPath;
      System.out.println(path_);
    } else {
      System.out.println("cd: no such file or directory: " + directory);
    }
  }

  private Path convertToPath(String str) {
    Path newPath = Paths.get(str);
    newPath = path_.resolve(newPath).normalize();
    return newPath;
  }
}
