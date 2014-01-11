package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.DeletedEvent;

import java.util.UUID;

public class PlayerDeletedEvent extends DeletedEvent {

  private UUID key;
  private PlayerDetails details;
  private boolean deletionCompleted;

  private PlayerDeletedEvent(UUID key) {
    this.key = key;
  }

  public PlayerDeletedEvent(UUID key, PlayerDetails details) {
    this.key = key;
    this.details = details;
    this.deletionCompleted = true;
  }

  public UUID getKey() {
    return key;
  }

  public PlayerDetails getDetails() {
    return details;
  }

  public boolean isDeletionCompleted() {
    return deletionCompleted;
  }

  public static PlayerDeletedEvent deletionForbidden(UUID key, PlayerDetails details) {
    PlayerDeletedEvent ev = new PlayerDeletedEvent(key, details);
    ev.entityFound=true;
    ev.deletionCompleted=false;
    return ev;
  }

  public static PlayerDeletedEvent notFound(UUID key) {
    PlayerDeletedEvent ev = new PlayerDeletedEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
