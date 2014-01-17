package com.yummynoodlebar.events.orders;

import java.util.Date;
import java.util.UUID;

public class TeamStatusDetails {

	private UUID teamId;
	private UUID id;
	private Date statusDate;
	private String status;

	public TeamStatusDetails(UUID teamId, UUID id, Date statusDate,
			String status) {
		this.id = id;
		this.teamId = teamId;
		this.status = status;
		this.statusDate = statusDate;
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
}
