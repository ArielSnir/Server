package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.ReadEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AllPlayersEvent extends ReadEvent {

  private final List<PlayerDetails> playersDetails;

  public AllPlayersEvent(List<PlayerDetails> players) {
    this.playersDetails = Collections.unmodifiableList(players);
  }

  public Collection<PlayerDetails> getPlayersDetails() {
    return this.playersDetails;
  }
}
