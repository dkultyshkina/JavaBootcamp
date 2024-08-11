package edu.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.*;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.*;

public class DataSourceProperties {
  private final String PROPERTIES_FILE =
      Paths
          .get(new File(".").getAbsoluteFile().getParentFile().getAbsoluteFile()
              + "/Chat/src/main/resources/db.properties")
          .toString();

  private final HikariDataSource hikariDataSource;

  public HikariDataSource getHikariDataSource() {
    return hikariDataSource;
  }

  public DataSourceProperties() {
    try {
      FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE);
      Properties properties = new Properties();
      properties.load(fileInputStream);
      String DB_USERNAME = (String) properties.get("USERNAME");
      String DB_PASSWORD = (String) properties.get("PASSWORD");
      String DB_URL = (String) properties.get("URL");
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(DB_URL);
      config.setUsername(DB_USERNAME);
      config.setPassword(DB_PASSWORD);
      hikariDataSource = new HikariDataSource(config);
      Connection connectionHikariDataSource = hikariDataSource.getConnection();
      connectionHikariDataSource.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
