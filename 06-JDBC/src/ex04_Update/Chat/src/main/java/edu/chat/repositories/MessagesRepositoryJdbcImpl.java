package edu.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.chat.exception.*;
import edu.chat.models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

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

  public Optional<Message> save(Message message) {
    String queryUpdate =
        "insert into chat.Message(message_author, message_room, message_text) values (?, ?, ?);";
    try (Connection connection = hikariDataSource.getConnection();
         PreparedStatement statementUpdate = connection.prepareStatement(queryUpdate)) {
      statementUpdate.setLong(1, message.getAuthor().getId());
      statementUpdate.setLong(2, message.getRoom().getId());
      statementUpdate.setString(3, message.getText());
      statementUpdate.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    saveUser(message);
    saveChatroom(message);
    return getMessage(message);
  }

  public void update(Message message) {
    if (message == null) {
      throw new NotUpdateEntityException(
          "Message have no ID existing in the database assigned for update");
    }
    String queryUpdate =
        "update chat.Message set message_author = ?, message_room = ?, message_text = ?, message_date = ? where message_id = ?";
    try (Connection connection = hikariDataSource.getConnection();
         PreparedStatement statementUpdate = connection.prepareStatement(queryUpdate)) {
      statementUpdate.setLong(1, message.getAuthor().getId());
      statementUpdate.setLong(2, message.getRoom().getId());
      statementUpdate.setString(3, message.getText());
      statementUpdate.setTimestamp(4, message.getDateTime());
      statementUpdate.setLong(5, message.getAuthor().getId());
      statementUpdate.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void saveUser(Message message) {
    User user = findUserById(message.getAuthor().getId());
    if (user == null) {
      throw new NotSavedSubEntityException("Author have no ID existing in the database assigned");
    }
  }

  private void saveChatroom(Message message) {
    Chatroom room = findChatroomById(message.getRoom().getId());
    if (room == null) {
      throw new NotSavedSubEntityException("Room have no ID existing in the database assigned");
    }
  }

  private Optional<Message> getMessage(Message message) {
    String querySelect =
        "SELECT * FROM chat.message WHERE message_author = ? and message_room = ? and message_text = ? order by message_id desc;";
    try (Connection connection = hikariDataSource.getConnection();
         PreparedStatement statementSelect = connection.prepareStatement(querySelect)) {
      statementSelect.setLong(1, message.getAuthor().getId());
      statementSelect.setLong(2, message.getRoom().getId());
      statementSelect.setString(3, message.getText());
      ResultSet resultSelect = statementSelect.executeQuery();
      if (resultSelect.next()) {
        User user = findUserById(resultSelect.getLong("message_author"));
        Chatroom chatroom = findChatroomById(resultSelect.getLong("message_room"));
        return Optional.of(new Message(resultSelect.getLong("message_id"), user, chatroom,
            resultSelect.getString("message_text"), resultSelect.getTimestamp("message_date")));
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
        User user = findUserById(result.getLong("chatroom_owner"));
        return new Chatroom(result.getLong("chatroom_id"), result.getString("chatroom_name"), user);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return null;
  }
}
