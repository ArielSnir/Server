package com.yummynoodlebar.rest.domain;

import com.yummynoodlebar.events.orders.PlayerStatusDetails;
import com.yummynoodlebar.rest.controller.PlayerQueriesController;
import com.yummynoodlebar.rest.controller.PlayerStatusControllerREST;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@XmlRootElement
public class PlayerStatus extends ResourceSupport {

  @XmlElement
  private UUID playerId;

  @XmlElement
  private Date statusDate;

  @XmlElement
  private String status;

  public static PlayerStatus fromPlayerStatusDetails(UUID key, PlayerStatusDetails playerDetails) {
    PlayerStatus status = new PlayerStatus();

    status.playerId = key;
    status.status = playerDetails.getStatus();
    status.statusDate = playerDetails.getStatusDate();

    status.add(linkTo(PlayerStatusControllerREST.class, key.toString()).withSelfRel());
    status.add(linkTo(PlayerQueriesController.class).slash(key).withRel("Player"));

    return status;
  }

  public UUID getPlayerId() {
    return playerId;
  }

  public Date getStatusDate() {
    return statusDate;
  }

  public String getStatus() {
    return status;
  }
}
