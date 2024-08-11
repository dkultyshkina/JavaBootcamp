package edu.chat.models;

import java.util.Objects;

public class Message {
  private int id;
  private int author;
  private int room;
  private String text;
  private String dateTime;

  public Message(int newAuthor, int newRoom, String newText, String newDateTime) {
    author = newAuthor;
    room = newRoom;
    text = newText;
    dateTime = newDateTime;
  }

  public int getId() {
    return id;
  }

  public int getAuthor() {
    return author;
  }

  public int getRoom() {
    return room;
  }

  public String getText() {
    return text;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setId(int newId) {
    id = newId;
  }

  public void setAuthor(int newAuthor) {
    author = newAuthor;
  }

  public void setRoom(int newRoom) {
    room = newRoom;
  }

  public void setText(String newText) {
    text = newText;
  }

  public void setDateTime(String newDateTime) {
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
    return "Chatroom{"
        + "id=" + id + ", author='" + author + '\'' + ", room='" + room + '\'' + ", text='" + text
        + '\'' + ", dateTime=" + dateTime + '}';
  }
}
