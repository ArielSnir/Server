package com.yummynoodlebar.events.orders;

import java.util.Date;
import java.util.UUID;

public class PlayerStatusDetails {

	private UUID playerId;
	private UUID id;
	private Date statusDate;
	private String status;

	public PlayerStatusDetails(UUID playerId, UUID id, Date statusDate,
			String status) {
		this.id = id;
		this.playerId = playerId;
		this.status = status;
		this.statusDate = statusDate;
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
}
