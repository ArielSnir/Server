package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.UpdatedEvent;

import java.util.UUID;

public class PlayerUpdatedEvent extends UpdatedEvent {

  private UUID key;
  private PlayerDetails playerDetails;

  public PlayerUpdatedEvent(UUID key, PlayerDetails playerDetails) {
    this.key = key;
    this.playerDetails = playerDetails;
  }

  public PlayerUpdatedEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }

  public PlayerDetails getPlayerDetails() {
    return playerDetails;
  }

  public static PlayerUpdatedEvent notFound(UUID key) {
    PlayerUpdatedEvent ev = new PlayerUpdatedEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
