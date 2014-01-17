package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.ReadEvent;

import java.util.UUID;

public class TeamDetailsEvent extends ReadEvent {
  private UUID key;
  private TeamDetails teamDetails;

  private TeamDetailsEvent(UUID key) {
    this.key = key;
  }

  public TeamDetailsEvent(UUID key, TeamDetails teamDetails) {
    this.key = key;
    this.teamDetails = teamDetails;
  }

  public UUID getKey() {
    return key;
  }

  public TeamDetails getTeamDetails() {
    return teamDetails;
  }

  public static TeamDetailsEvent notFound(UUID key) {
    TeamDetailsEvent ev = new TeamDetailsEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
