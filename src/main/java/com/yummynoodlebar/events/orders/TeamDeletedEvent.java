package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.DeletedEvent;

import java.util.UUID;

public class TeamDeletedEvent extends DeletedEvent {

  private UUID key;
  private TeamDetails details;
  private boolean deletionCompleted;

  private TeamDeletedEvent(UUID key) {
    this.key = key;
  }

  public TeamDeletedEvent(UUID key, TeamDetails details) {
    this.key = key;
    this.details = details;
    this.deletionCompleted = true;
  }

  public UUID getKey() {
    return key;
  }

  public TeamDetails getDetails() {
    return details;
  }

  public boolean isDeletionCompleted() {
    return deletionCompleted;
  }

  public static TeamDeletedEvent deletionForbidden(UUID key, TeamDetails details) {
    TeamDeletedEvent ev = new TeamDeletedEvent(key, details);
    ev.entityFound=true;
    ev.deletionCompleted=false;
    return ev;
  }

  public static TeamDeletedEvent notFound(UUID key) {
    TeamDeletedEvent ev = new TeamDeletedEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
