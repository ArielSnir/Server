package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.ReadEvent;

import java.util.UUID;

public class TeamStatusEvent extends ReadEvent {
  private UUID key;
  private TeamStatusDetails teamStatus;

  private TeamStatusEvent(UUID key) {
    this.key = key;
  }

  public TeamStatusEvent(UUID key, TeamStatusDetails teamStatus) {
    this.key = key;
    this.teamStatus = teamStatus;
  }

  public UUID getKey() {
    return key;
  }

  public TeamStatusDetails getTeamStatus() {
    return teamStatus;
  }

  public static TeamStatusEvent notFound(UUID key) {
    TeamStatusEvent ev = new TeamStatusEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
