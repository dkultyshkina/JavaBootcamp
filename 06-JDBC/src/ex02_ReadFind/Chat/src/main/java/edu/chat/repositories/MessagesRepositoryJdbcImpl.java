package edu.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import edu.chat.models.*;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
  private final HikariDataSource hikariDataSource;
  public MessagesRepositoryJdbcImpl(HikariDataSource hikariDataSource) {
    this.hikariDataSource = hikariDataSource;
  }

  public Optional<Message> findById(Long id) {
    String query = "SELECT * FROM chat.message WHERE message_id = ?";
    try (Connection connection = hikariDataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
      if (result.next()) {
        User user = findUserById(result.getLong("message_author"));
        Chatroom chatroom = findChatroomById(result.getLong("message_room"));
        return Optional.of(new Message(result.getLong("message_id"), user, chatroom,
            result.getString("message_text"), result.getTimestamp("message_date")));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return Optional.empty();
  }

  private User findUserById(long id) {
    String query = "SELECT * FROM chat.user WHERE user_id = ?";
    try (Connection connection = hikariDataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
      if (result.next()) {
        return new User(result.getLong("user_id"), result.getString("user_login"),
            result.getString("user_password"));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  private Chatroom findChatroomById(long id) {
    String query = "SELECT * FROM chat.chatroom WHERE chatroom_id = ?";
    try (Connection connection = hikariDataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
      if (result.next()) {
        return new Chatroom(result.getLong("chatroom_id"), result.getString("chatroom_name"),
            result.getString("chatroom_owner"));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return null;
  }
}
