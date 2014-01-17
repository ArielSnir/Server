package com.yummynoodlebar.rest.domain;

import com.yummynoodlebar.events.orders.TeamStatusDetails;
import com.yummynoodlebar.rest.controller.TeamQueriesController;
import com.yummynoodlebar.rest.controller.TeamStatusControllerREST;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@XmlRootElement
public class TeamStatus extends ResourceSupport {

  @XmlElement
  private UUID teamId;

  @XmlElement
  private Date statusDate;

  @XmlElement
  private String status;

  public static TeamStatus fromTeamStatusDetails(UUID key, TeamStatusDetails teamDetails) {
    TeamStatus status = new TeamStatus();

    status.teamId = key;
    status.status = teamDetails.getStatus();
    status.statusDate = teamDetails.getStatusDate();

    status.add(linkTo(TeamStatusControllerREST.class, key.toString()).withSelfRel());
    status.add(linkTo(TeamQueriesController.class).slash(key).withRel("Team"));

    return status;
  }

  public UUID getTeamId() {
    return teamId;
  }

  public Date getStatusDate() {
    return statusDate;
  }

  public String getStatus() {
    return status;
  }
}
