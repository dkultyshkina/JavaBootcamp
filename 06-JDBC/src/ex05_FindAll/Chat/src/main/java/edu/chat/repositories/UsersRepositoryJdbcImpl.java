package edu.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.chat.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {
  private final HikariDataSource hikariDataSource;
  public UsersRepositoryJdbcImpl(HikariDataSource hikariDataSource) {
    this.hikariDataSource = hikariDataSource;
  }

  public List<User> findAll(int page, int size) {
    String query = "SELECT * FROM chat.User offset ? limit ?";
    List<User> users = new ArrayList<>();
    users = null;
    try (Connection connection = hikariDataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, page * size);
      statement.setInt(2, size);
      ResultSet result = statement.executeQuery();
      users = new ArrayList<>();
      while (result.next()) {
        users.add(new User(result.getLong("user_id"), result.getString("user_login"),
            result.getString("user_password")));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return users;
  }
}
