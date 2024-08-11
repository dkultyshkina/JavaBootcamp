package edu.chat.models;

import java.util.ArrayList;
import java.util.Objects;

public class User {
  private long id;
  private String login;
  private String password;
  private ArrayList<Chatroom> createdRooms;
  private ArrayList<Chatroom> usedRooms;

  public User(long newId, String newLogin, String newPassword) {
    id = newId;
    login = newLogin;
    password = newPassword;
  }

  public long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public ArrayList<Chatroom> getCreatedRooms() {
    return createdRooms;
  }

  public ArrayList<Chatroom> getUsedRooms() {
    return usedRooms;
  }

  public void setId(long newId) {
    id = newId;
  }

  public void setLogin(String newLogin) {
    login = newLogin;
  }

  public void setPassword(String newPassword) {
    password = newPassword;
  }

  public void setCreatedRooms(ArrayList<Chatroom> newCreatedRooms) {
    createdRooms = newCreatedRooms;
  }

  public void setUsedRooms(ArrayList<Chatroom> newUsedRooms) {
    usedRooms = newUsedRooms;
  }

  public void addCreatedRoom(Chatroom room) {
    createdRooms.add(room);
  }

  public void addUsedRoom(Chatroom room) {
    usedRooms.add(room);
  }

  public boolean equals(Object obj) {
    if (this.equals(obj)) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    User user = (User) obj;
    return id == user.id && Objects.equals(login, user.login)
        && Objects.equals(password, user.password)
        && Objects.equals(createdRooms, user.createdRooms)
        && Objects.equals(usedRooms, user.usedRooms);
  }

  public int hashCode() {
    return Objects.hash(id, login, password, createdRooms, usedRooms);
  }

  public String toString() {
    return "{"
        + "id=" + id + ", login='" + login + '\'' + ", password='" + password + '\''
        + ", createdRooms=" + createdRooms + ", usedRooms=" + usedRooms + '}';
  }
}
