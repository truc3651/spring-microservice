package com.example.api.event;

import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class Event<K, T> {
  public enum Type {CREATE, DELETE}
  private Event.Type eventType;
  private K key;
  private T data;
  private ZonedDateTime eventCreatedAt;

  public Event(Type eventType, K key, T data) {
    this.eventType = eventType;
    this.key = key;
    this.data = data;
  }
}
