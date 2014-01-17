package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.DeleteEvent;

import java.util.UUID;

public class DeleteTeamEvent extends DeleteEvent {

  private final UUID key;

  public DeleteTeamEvent(final UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }
}
