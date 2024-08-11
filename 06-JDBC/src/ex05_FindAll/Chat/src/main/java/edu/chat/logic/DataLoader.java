package edu.chat.logic;

import com.zaxxer.hikari.HikariDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class DataLoader {
  private final String SCHEMA_FILE =
      Paths
          .get(new File(".").getAbsoluteFile().getParentFile().getAbsoluteFile()
              + "/Chat/src/main/resources/schema.sql")
          .toString();

  private final String DATA_FILE =
      Paths
          .get(new File(".").getAbsoluteFile().getParentFile().getAbsoluteFile()
              + "/Chat/src/main/resources/data.sql")
          .toString();
  private final HikariDataSource hikariDataSource;
  public DataLoader(HikariDataSource hikariDataSource) {
    this.hikariDataSource = hikariDataSource;
  }

  public void loadData() {
    executeSQLFile(SCHEMA_FILE);
    executeSQLFile(DATA_FILE);
  }

  private void executeSQLFile(String filename) {
    try {
      Connection connection = hikariDataSource.getConnection();
      Statement statement = connection.createStatement();
      InputStream inputStream = new FileInputStream(filename);
      Scanner scanner = new Scanner(inputStream).useDelimiter(";");
      while (scanner.hasNext()) {
        statement.addBatch(scanner.next().trim());
      }
      statement.executeBatch();
      statement.close();
      scanner.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
