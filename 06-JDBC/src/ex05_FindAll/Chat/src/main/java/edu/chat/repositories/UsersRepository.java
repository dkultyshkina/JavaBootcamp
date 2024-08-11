package edu.chat.repositories;

import edu.chat.models.*;
import java.util.List;

public interface UsersRepository {
  List<User> findAll(int page, int size);
}
