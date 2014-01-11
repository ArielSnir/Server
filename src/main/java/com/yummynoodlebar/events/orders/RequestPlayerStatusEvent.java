package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.RequestReadEvent;

import java.util.UUID;

public class RequestPlayerStatusEvent extends RequestReadEvent {
  private UUID key;

  public RequestPlayerStatusEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }
}
