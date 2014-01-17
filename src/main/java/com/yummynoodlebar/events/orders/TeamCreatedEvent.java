package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.CreatedEvent;

import java.util.UUID;

public class TeamCreatedEvent extends CreatedEvent {

  private final UUID newTeamKey;
  private final TeamDetails details;

  public TeamCreatedEvent(final UUID newTeamKey, final TeamDetails details) {
    this.newTeamKey = newTeamKey;
    this.details = details;
  }

  public TeamDetails getDetails() {
    return details;
  }

  public UUID getNewTeamKey() {
    return newTeamKey;
  }
}
