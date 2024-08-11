package edu.chat.exception;

public class NotSavedSubEntityException extends RuntimeException {
  public NotSavedSubEntityException(String message) {
    super(message);
  }
}
