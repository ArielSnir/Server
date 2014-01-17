package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.ReadEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AllTeamsEvent extends ReadEvent {

  private final List<TeamDetails> teamsDetails;

  public AllTeamsEvent(List<TeamDetails> teams) {
    this.teamsDetails = Collections.unmodifiableList(teams);
  }

  public Collection<TeamDetails> getTeamsDetails() {
    return this.teamsDetails;
  }
}
