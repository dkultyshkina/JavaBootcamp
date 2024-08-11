package edu.chat.app;

import edu.chat.logic.DataLoader;
import edu.chat.models.*;
import edu.chat.repositories.DataSourceProperties;
import edu.chat.repositories.MessagesRepositoryJdbcImpl;
import java.util.Optional;

public class Program {
  public static void main(String[] args) {
    DataSourceProperties dataSource = new DataSourceProperties();
    DataLoader loader = new DataLoader(dataSource.getHikariDataSource());
    loader.loadData();
    MessagesRepositoryJdbcImpl repository =
        new MessagesRepositoryJdbcImpl(dataSource.getHikariDataSource());
    Optional<Message> messageOptional = repository.findById(11L);
    if (!messageOptional.isPresent()) {
      System.out.println("Message have no ID existing in the database assigned");
    }
    messageOptional = repository.findById(3L);
    if (messageOptional.isPresent()) {
      Message message = messageOptional.get();
      message.setText("Bye");
      message.setDateTime(null);
      repository.update(message);
      Optional<Message> result = repository.findById(3L);
      System.out.println(result.toString());
    }
  }
}
