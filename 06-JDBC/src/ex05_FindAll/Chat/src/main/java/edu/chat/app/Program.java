package edu.chat.app;

import edu.chat.logic.DataLoader;
import edu.chat.models.*;
import edu.chat.repositories.*;
import edu.chat.repositories.DataSourceProperties;
import java.util.List;

public class Program {
  public static void main(String[] args) {
    DataSourceProperties dataSource = new DataSourceProperties();
    DataLoader loader = new DataLoader(dataSource.getHikariDataSource());
    loader.loadData();
    UsersRepositoryJdbcImpl repository =
        new UsersRepositoryJdbcImpl(dataSource.getHikariDataSource());
    List<User> users = repository.findAll(1, 2);
    for (User user : users) {
      System.out.println(user.toString());
    }
  }
}
