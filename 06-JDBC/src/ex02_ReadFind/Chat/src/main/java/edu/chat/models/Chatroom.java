package edu.chat.models;

import java.util.ArrayList;
import java.util.Objects;

public class Chatroom {
  private long id;
  private String name;
  private String owner;
  private ArrayList<Message> messagesChatroom;

  public Chatroom(long newId, String newName, String newOwner) {
    id = newId;
    name = newName;
    owner = newOwner;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getOwner() {
    return owner;
  }

  public ArrayList<Message> getMessagesChatroom() {
    return messagesChatroom;
  }

  public void setId(long newId) {
    id = newId;
  }

  public void setName(String newName) {
    name = newName;
  }

  public void setOwner(String newOwner) {
    owner = newOwner;
  }

  public void setMessagesChatroom(ArrayList<Message> newMessagesChatroom) {
    messagesChatroom = newMessagesChatroom;
  }

  public void addMessage(Message message) {
    messagesChatroom.add(message);
  }

  public boolean equals(Object obj) {
    if (this.equals(obj)) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Chatroom chatroom = (Chatroom) obj;
    return id == chatroom.id && Objects.equals(name, chatroom.name)
        && Objects.equals(owner, chatroom.owner)
        && Objects.equals(messagesChatroom, chatroom.messagesChatroom);
  }

  public int hashCode() {
    return Objects.hash(id, name, owner, messagesChatroom);
  }

  public String toString() {
    return "{"
        + "id=" + id + ", name='" + name + '\'' + ", owner='" + owner + '\''
        + ", messagesChatroom=" + messagesChatroom + '}';
  }
}
