package com.yummynoodlebar.persistence.domain;

import com.yummynoodlebar.persistence.domain.Player;
import com.yummynoodlebar.events.orders.TeamDetails;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Entity(name = "NOODLE_TEAMS")
public class Team {
	private String name;



	@Column(name = "SUBMISSION_DATETIME")
	private Date dateTimeOfSubmission;

	@ElementCollection(fetch = FetchType.EAGER, targetClass = java.lang.Integer.class)
	@JoinTable(name="TEAM_TEAM_ITEMS", joinColumns=@JoinColumn(name="ID"))
	@MapKeyColumn(name="MENU_ID")
	@Column(name="VALUE")
	private Map<String, Integer> teamItems;
	
	
	
//	  private List<Account> accounts;
	@DBRef
	private Set<Player> players;

	@Transient
	private TeamStatus teamStatus;

	@Id
	@Column(name = "TEAM_ID")
	private String id;

	public void setId(String id) {
		this.id = id;
	}

	public void setDateTimeOfSubmission(Date dateTimeOfSubmission) {
		this.dateTimeOfSubmission = dateTimeOfSubmission;
	}

	public TeamStatus getStatus() {
		return teamStatus;
	}

	public void setStatus(TeamStatus teamStatus) {
		this.teamStatus = teamStatus;
	}

	public Date getDateTimeOfSubmission() {
		return dateTimeOfSubmission;
	}

	public String getId() {
		return id;
	}

	public void setTeamItems(Map<String, Integer> teamItems) {
		if (teamItems == null) {
			this.teamItems = Collections.emptyMap();
		} else {
			this.teamItems = Collections.unmodifiableMap(teamItems);
		}
	}

	public Map<String, Integer> getTeamItems() {
		return teamItems;
	}

	public TeamDetails toTeamDetails() {
		TeamDetails details = new TeamDetails();
		details.setName(this.name);
		details.setKey(UUID.fromString(this.id));
		details.setDateTimeOfSubmission(this.dateTimeOfSubmission);
		details.setTeamItems(this.getTeamItems());

		return details;
	}

	public static Team fromTeamDetails(TeamDetails teamDetails) {
		Team team = new Team();

		team.name = (teamDetails.getName()!=null) ? teamDetails.getName().toString() : null;
		team.id = (teamDetails.getKey()!=null) ? teamDetails.getKey().toString() : null;
		team.dateTimeOfSubmission = teamDetails.getDateTimeOfSubmission();
		team.teamItems = teamDetails.getTeamItems();

		return team;
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
	
	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
}