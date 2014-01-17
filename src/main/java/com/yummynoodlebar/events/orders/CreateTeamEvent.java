package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.CreateEvent;

public class CreateTeamEvent extends CreateEvent {
  private TeamDetails details;

  public CreateTeamEvent(TeamDetails details) {
    this.details = details;
  }

  public TeamDetails getDetails() {
    return details;
  }
}
