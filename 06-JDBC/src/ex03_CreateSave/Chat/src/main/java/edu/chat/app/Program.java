package edu.chat.app;

import edu.chat.logic.DataLoader;
import edu.chat.models.*;
import edu.chat.repositories.DataSourceProperties;
import edu.chat.repositories.MessagesRepositoryJdbcImpl;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class Program {
  public static void main(String[] args) {
    User creator = new User(1, "user1", "password1");
    User author = creator;
    Chatroom room = new Chatroom(5, "room5", creator);
    Message message = new Message(author, room, "Hello!", Timestamp.valueOf(LocalDateTime.now()));
    User creatorTwo = new User(7L, "user", "password");
    User authorTwo = creator;
    Chatroom roomTwo = new Chatroom(8L, "room", creatorTwo);
    Message messageTwo =
        new Message(authorTwo, roomTwo, "Hello!", Timestamp.valueOf(LocalDateTime.now()));
    DataSourceProperties dataSource = new DataSourceProperties();
    DataLoader loader = new DataLoader(dataSource.getHikariDataSource());
    loader.loadData();
    MessagesRepositoryJdbcImpl repository =
        new MessagesRepositoryJdbcImpl(dataSource.getHikariDataSource());
    Optional<Message> resultMessage = repository.save(message);
    System.out.println(resultMessage.toString());
    Optional<Message> resultMessageTwo = repository.save(messageTwo);
  }
}
