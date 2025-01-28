package com.example.util;

import java.time.OffsetDateTime;
import org.springframework.http.HttpStatus;

public class ErrorMessage {
  private HttpStatus httpStatus;
  private OffsetDateTime timestamp;
  private String path;
  private String message;

  public ErrorMessage(HttpStatus httpStatus, String path, String message) {
    this.httpStatus = httpStatus;
    this.timestamp = OffsetDateTime.now();
    this.path = path;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
