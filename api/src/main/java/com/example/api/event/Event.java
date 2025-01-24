package com.example.api.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@Data
@JsonTypeName("event")
public class Event<K, T> {
  public enum Type {CREATE, DELETE}
  private Event.Type eventType;
  private K key;
  private T data;

  public Event() {
  }

  public Event(Type eventType, K key, T data) {
    this.eventType = eventType;
    this.key = key;
    this.data = data;
  }

  public Event(Type eventType, K key) {
    this.eventType = eventType;
    this.key = key;
  }
}
