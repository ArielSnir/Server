package com.yummynoodlebar.core.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.yummynoodlebar.events.orders.TeamDetails;

public class Team {
	private String name;


	private final Date dateTimeOfSubmission;
	private List<MenuItem> menuItems;
	private final UUID key;
	private Customer customer;

	private TeamStatus status;
	private List<TeamStatus> statusHistory;

	private Date expectedCompletionTime;
	private BigDecimal totalCost;

	//currently 5 minutes
	private final static long ACCEPT_CANCEL_TIME = 1000 * 60 * 5;

	public Team(final Date dateTimeOfSubmission) {
		this.key = UUID.randomUUID();
		this.dateTimeOfSubmission = dateTimeOfSubmission;
		statusHistory = new ArrayList<TeamStatus>();
	}

	public Team(final String name,final Date dateTimeOfSubmission) {
		this.name = name;
		this.key = UUID.randomUUID();
		this.dateTimeOfSubmission = dateTimeOfSubmission;
		statusHistory = new ArrayList<TeamStatus>();
	}

	public Team(final UUID key,final Date dateTimeOfSubmission) {
		this.key = key;
		this.dateTimeOfSubmission = dateTimeOfSubmission;
		statusHistory = new ArrayList<TeamStatus>();
	}

	public Team(final UUID key,final String name,final Date dateTimeOfSubmission) {
		this.key = key;
		this.name = name;
		this.dateTimeOfSubmission = dateTimeOfSubmission;
		statusHistory = new ArrayList<TeamStatus>();
	}
	
	public Date getExpectedCompletionTime() {
		return expectedCompletionTime;
	}

	public void setExpectedCompletionTime(Date expectedCompletionTime) {
		this.expectedCompletionTime = expectedCompletionTime;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public void addStatus(TeamStatus newStatus) {
		statusHistory.add(newStatus);
		status = newStatus;
	}

	public TeamStatus getStatus() {
		return status;
	}

	public Date getDateTimeOfSubmission() {
		return dateTimeOfSubmission;
	}

	public UUID getKey() {
		return key;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public boolean canBeDeleted() {
		//accept cancellation if within 5 minutes of placing.
		return System.currentTimeMillis() - dateTimeOfSubmission.getTime() < ACCEPT_CANCEL_TIME;
	}

	public Team withMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
		return this;
	}

	public TeamDetails toTeamDetails() {
		TeamDetails details = new TeamDetails();

		details.setName(getName());
		details.setDateTimeOfSubmission(getDateTimeOfSubmission());
		details.setKey(getKey());

		return details;
	}

	public static Team fromTeamDetails(TeamDetails teamDetails) {
		Team team = new Team(teamDetails.getKey(),teamDetails.getName(),teamDetails.getDateTimeOfSubmission());

		BeanUtils.copyProperties(teamDetails, team);

		return team;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
