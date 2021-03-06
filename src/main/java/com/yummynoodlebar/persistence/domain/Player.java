package com.yummynoodlebar.persistence.domain;

import com.yummynoodlebar.events.orders.PlayerDetails;

import javax.persistence.*;
import org.springframework.data.annotation.Id;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Entity(name = "NOODLE_PLAYERS")
public class Player {
	private String name;

	public Player(){};
	public Player(String name, Date dateTimeOfSubmission,UUID id) {
	    this.name = name;
	    this.dateTimeOfSubmission = dateTimeOfSubmission;
	    this.id = id.toString();
	  }

	@Column(name = "SUBMISSION_DATETIME")
	private Date dateTimeOfSubmission;

	@ElementCollection(fetch = FetchType.EAGER, targetClass = java.lang.Integer.class)
	@JoinTable(name="PLAYER_PLAYER_ITEMS", joinColumns=@JoinColumn(name="ID"))
	@MapKeyColumn(name="MENU_ID")
	@Column(name="VALUE")
	private Map<String, Integer> playerItems;

	@Transient
	private PlayerStatus playerStatus;

	@Column(name = "PLAYER_ID")
	@Id
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
		details.setName(this.name);
		details.setKey(UUID.fromString(this.id));
		details.setDateTimeOfSubmission(this.dateTimeOfSubmission);
		details.setPlayerItems(this.getPlayerItems());

		return details;
	}

	public static Player fromPlayerDetails(PlayerDetails playerDetails) {
		Player player = new Player();

		player.name = (playerDetails.getName()!=null) ? playerDetails.getName().toString() : null;
		player.id = (playerDetails.getKey()!=null) ? playerDetails.getKey().toString() : null;
		player.dateTimeOfSubmission = playerDetails.getDateTimeOfSubmission();
		player.playerItems = playerDetails.getPlayerItems();

		return player;
	}

	public UUID getKey() {
		// TODO Auto-generated method stub
		return UUID.fromString(this.id);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}