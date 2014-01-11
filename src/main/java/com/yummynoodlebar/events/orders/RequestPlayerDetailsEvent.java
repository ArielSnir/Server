package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.RequestReadEvent;

import java.util.UUID;

public class RequestPlayerDetailsEvent extends RequestReadEvent {
  private UUID key;

  public RequestPlayerDetailsEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }
}
