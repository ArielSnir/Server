package com.yummynoodlebar.persistence.domain;

import com.yummynoodlebar.events.orders.PlayerDetails;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Entity(name = "NOODLE_PLAYERS")
public class Player {

  @Column(name = "SUBMISSION_DATETIME")
  private Date dateTimeOfSubmission;

  @ElementCollection(fetch = FetchType.EAGER, targetClass = java.lang.Integer.class)
  @JoinTable(name="PLAYER_PLAYER_ITEMS", joinColumns=@JoinColumn(name="ID"))
  @MapKeyColumn(name="MENU_ID")
  @Column(name="VALUE")
  private Map<String, Integer> playerItems;

  @Transient
  private PlayerStatus playerStatus;

  @Id
  @Column(name = "PLAYER_ID")
  private String id;

  public void setId(String id) {
    this.id = id;
  }

  public void setDateTimeOfSubmission(Date dateTimeOfSubmission) {
    this.dateTimeOfSubmission = dateTimeOfSubmission;
  }

  public PlayerStatus getStatus() {
    return playerStatus;
  }

  public void setStatus(PlayerStatus playerStatus) {
    this.playerStatus = playerStatus;
  }

  public Date getDateTimeOfSubmission() {
    return dateTimeOfSubmission;
  }

  public String getId() {
    return id;
  }

  public void setPlayerItems(Map<String, Integer> playerItems) {
    if (playerItems == null) {
      this.playerItems = Collections.emptyMap();
    } else {
      this.playerItems = Collections.unmodifiableMap(playerItems);
    }
  }

  public Map<String, Integer> getPlayerItems() {
    return playerItems;
  }

  public PlayerDetails toPlayerDetails() {
    PlayerDetails details = new PlayerDetails();

    details.setKey(UUID.fromString(this.id));
    details.setDateTimeOfSubmission(this.dateTimeOfSubmission);
    details.setPlayerItems(this.getPlayerItems());

    return details;
  }

  public static Player fromPlayerDetails(PlayerDetails playerDetails) {
    Player player = new Player();

    player.id = (playerDetails.getKey()!=null) ? playerDetails.getKey().toString() : null;
    player.dateTimeOfSubmission = playerDetails.getDateTimeOfSubmission();
    player.playerItems = playerDetails.getPlayerItems();

    return player;
  }

public UUID getKey() {
	// TODO Auto-generated method stub
	return UUID.fromString(this.id);
}
}