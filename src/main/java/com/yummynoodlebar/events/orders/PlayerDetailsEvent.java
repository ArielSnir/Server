package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.ReadEvent;

import java.util.UUID;

public class PlayerDetailsEvent extends ReadEvent {
  private UUID key;
  private PlayerDetails playerDetails;

  private PlayerDetailsEvent(UUID key) {
    this.key = key;
  }

  public PlayerDetailsEvent(UUID key, PlayerDetails playerDetails) {
    this.key = key;
    this.playerDetails = playerDetails;
  }

  public UUID getKey() {
    return key;
  }

  public PlayerDetails getPlayerDetails() {
    return playerDetails;
  }

  public static PlayerDetailsEvent notFound(UUID key) {
    PlayerDetailsEvent ev = new PlayerDetailsEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
