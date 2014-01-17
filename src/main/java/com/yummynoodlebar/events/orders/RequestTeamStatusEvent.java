package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.RequestReadEvent;

import java.util.UUID;

public class RequestTeamStatusEvent extends RequestReadEvent {
  private UUID key;

  public RequestTeamStatusEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }
}
