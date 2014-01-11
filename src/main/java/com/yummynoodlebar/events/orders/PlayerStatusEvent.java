package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.ReadEvent;

import java.util.UUID;

public class PlayerStatusEvent extends ReadEvent {
  private UUID key;
  private PlayerStatusDetails playerStatus;

  private PlayerStatusEvent(UUID key) {
    this.key = key;
  }

  public PlayerStatusEvent(UUID key, PlayerStatusDetails playerStatus) {
    this.key = key;
    this.playerStatus = playerStatus;
  }

  public UUID getKey() {
    return key;
  }

  public PlayerStatusDetails getPlayerStatus() {
    return playerStatus;
  }

  public static PlayerStatusEvent notFound(UUID key) {
    PlayerStatusEvent ev = new PlayerStatusEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
