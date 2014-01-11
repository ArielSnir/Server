package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.CreateEvent;

public class CreatePlayerEvent extends CreateEvent {
  private PlayerDetails details;

  public CreatePlayerEvent(PlayerDetails details) {
    this.details = details;
  }

  public PlayerDetails getDetails() {
    return details;
  }
}
