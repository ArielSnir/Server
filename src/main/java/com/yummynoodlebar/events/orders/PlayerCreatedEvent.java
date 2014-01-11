package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.CreatedEvent;

import java.util.UUID;

public class PlayerCreatedEvent extends CreatedEvent {

  private final UUID newPlayerKey;
  private final PlayerDetails details;

  public PlayerCreatedEvent(final UUID newPlayerKey, final PlayerDetails details) {
    this.newPlayerKey = newPlayerKey;
    this.details = details;
  }

  public PlayerDetails getDetails() {
    return details;
  }

  public UUID getNewPlayerKey() {
    return newPlayerKey;
  }
}
