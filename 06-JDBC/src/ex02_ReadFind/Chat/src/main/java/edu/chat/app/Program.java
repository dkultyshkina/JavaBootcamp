package edu.chat.app;

import java.util.Optional;
import java.util.Scanner;
import edu.chat.logic.DataLoader;
import edu.chat.models.Message;
import edu.chat.repositories.DataSourceProperties;
import edu.chat.repositories.MessagesRepositoryJdbcImpl;

public class Program {
  public static void main(String[] args) {
    DataSourceProperties dataSource = new DataSourceProperties();
    DataLoader loader = new DataLoader(dataSource.getHikariDataSource());
    loader.loadData();
    MessagesRepositoryJdbcImpl repository =
        new MessagesRepositoryJdbcImpl(dataSource.getHikariDataSource());
    System.out.println("Enter a message ID");
    Scanner scanner = new Scanner(System.in);
    long id = scanner.nextInt();
    Optional<Message> message = repository.findById(id);
    System.out.println(message.toString());
    scanner.close();
  }
}
