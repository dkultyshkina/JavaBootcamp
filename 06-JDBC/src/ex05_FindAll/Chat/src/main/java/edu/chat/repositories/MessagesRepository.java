package edu.chat.repositories;

import edu.chat.models.Message;
import java.util.Optional;

public interface MessagesRepository {
  Optional<Message> findById(Long id);
  Optional<Message> save(Message message);
  void update(Message message);
}
