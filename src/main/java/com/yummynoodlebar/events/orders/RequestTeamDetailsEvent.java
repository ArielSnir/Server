package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.RequestReadEvent;

import java.util.UUID;

public class RequestTeamDetailsEvent extends RequestReadEvent {
  private UUID key;

  public RequestTeamDetailsEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }
}
