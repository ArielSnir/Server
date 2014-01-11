package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.UpdateEvent;

import java.util.UUID;

public class SetPlayerStatusEvent extends UpdateEvent {

  private UUID key;
  private PlayerStatusDetails playerStatus;

  public SetPlayerStatusEvent(UUID key, PlayerStatusDetails playerStatus) {
    this.key = key;
    this.playerStatus = playerStatus;
  }

  public UUID getKey() {
    return key;
  }

  public PlayerStatusDetails getPlayerStatus() {
    return playerStatus;
  }
}
