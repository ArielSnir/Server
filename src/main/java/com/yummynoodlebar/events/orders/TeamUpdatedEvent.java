package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.UpdatedEvent;

import java.util.UUID;

public class TeamUpdatedEvent extends UpdatedEvent {

  private UUID key;
  private TeamDetails teamDetails;

  public TeamUpdatedEvent(UUID key, TeamDetails teamDetails) {
    this.key = key;
    this.teamDetails = teamDetails;
  }

  public TeamUpdatedEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }

  public TeamDetails getTeamDetails() {
    return teamDetails;
  }

  public static TeamUpdatedEvent notFound(UUID key) {
    TeamUpdatedEvent ev = new TeamUpdatedEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
