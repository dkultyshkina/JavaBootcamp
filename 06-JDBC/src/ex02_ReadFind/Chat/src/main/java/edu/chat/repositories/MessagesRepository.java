package edu.chat.repositories;

import java.util.Optional;
import edu.chat.models.Message;

public interface MessagesRepository {
  Optional<Message> findById(Long id);
}
