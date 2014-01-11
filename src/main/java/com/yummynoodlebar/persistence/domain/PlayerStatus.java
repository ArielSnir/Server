package com.yummynoodlebar.persistence.domain;

import com.yummynoodlebar.events.orders.PlayerStatusDetails;

import java.util.Date;
import java.util.UUID;

public class PlayerStatus {

  private UUID playerId;
  private UUID id;
  private Date statusDate;
  private String status;

  public PlayerStatus(UUID playerId, UUID id, final Date date, final String status) {
    this.playerId = playerId;
    this.id = id;
    this.status = status;
    this.statusDate = date;
  }

  public Date getStatusDate() {
    return statusDate;
  }

  public String getStatus() {
    return status;
  }

  public UUID getPlayerId() {
    return playerId;
  }

  public UUID getId() {
    return id;
  }

  public PlayerStatusDetails toStatusDetails() {
    return new PlayerStatusDetails(playerId, id, statusDate, status);
  }

  public static PlayerStatus fromStatusDetails(PlayerStatusDetails playerStatusDetails) {
    return new PlayerStatus(
        playerStatusDetails.getPlayerId(), playerStatusDetails.getId(),
        playerStatusDetails.getStatusDate(), playerStatusDetails.getStatus());
  }
}
