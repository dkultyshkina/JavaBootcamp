package edu.chat.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Message {
  private long id;
  private User author;
  private Chatroom room;
  private String text;
  private Timestamp dateTime;

  public Message(
      long newId, User newAuthor, Chatroom newRoom, String newText, Timestamp newDateTime) {
    id = newId;
    author = newAuthor;
    room = newRoom;
    text = newText;
    dateTime = newDateTime;
  }

  public long getId() {
    return id;
  }

  public User getAuthor() {
    return author;
  }

  public Chatroom getRoom() {
    return room;
  }

  public String getText() {
    return text;
  }

  public Timestamp getDateTime() {
    return dateTime;
  }

  public void setId(long newId) {
    id = newId;
  }

  public void setAuthor(User newAuthor) {
    author = newAuthor;
  }

  public void setRoom(Chatroom newRoom) {
    room = newRoom;
  }

  public void setText(String newText) {
    text = newText;
  }

  public void setDateTime(Timestamp newDateTime) {
    dateTime = newDateTime;
  }

  public boolean equals(Object obj) {
    if (this.equals(obj)) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Message message = (Message) obj;
    return id == message.id && Objects.equals(author, message.author)
        && Objects.equals(room, message.room) && Objects.equals(text, message.text)
        && Objects.equals(dateTime, message.dateTime);
  }

  public int hashCode() {
    return Objects.hash(id, author, room, text, dateTime);
  }

  public String toString() {
    return "Message{"
        + "id=" + id + ", author='" + author.toString() + '\'' + ", room='" + room.toString() + '\''
        + ", text='" + text + '\'' + ", dateTime=" + dateTime + '}';
  }
}
