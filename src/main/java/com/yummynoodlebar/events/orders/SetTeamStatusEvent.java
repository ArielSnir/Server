package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.UpdateEvent;

import java.util.UUID;

public class SetTeamStatusEvent extends UpdateEvent {

  private UUID key;
  private TeamStatusDetails teamStatus;

  public SetTeamStatusEvent(UUID key, TeamStatusDetails teamStatus) {
    this.key = key;
    this.teamStatus = teamStatus;
  }

  public UUID getKey() {
    return key;
  }

  public TeamStatusDetails getTeamStatus() {
    return teamStatus;
  }
}
