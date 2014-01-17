package com.yummynoodlebar.persistence.domain;

import com.yummynoodlebar.events.orders.TeamStatusDetails;

import java.util.Date;
import java.util.UUID;

public class TeamStatus {

  private UUID teamId;
  private UUID id;
  private Date statusDate;
  private String status;

  public TeamStatus(UUID teamId, UUID id, final Date date, final String status) {
    this.teamId = teamId;
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

  public UUID getTeamId() {
    return teamId;
  }

  public UUID getId() {
    return id;
  }

  public TeamStatusDetails toStatusDetails() {
    return new TeamStatusDetails(teamId, id, statusDate, status);
  }

  public static TeamStatus fromStatusDetails(TeamStatusDetails teamStatusDetails) {
    return new TeamStatus(
        teamStatusDetails.getTeamId(), teamStatusDetails.getId(),
        teamStatusDetails.getStatusDate(), teamStatusDetails.getStatus());
  }
}
