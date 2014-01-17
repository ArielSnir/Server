package com.yummynoodlebar.core.services;

import com.yummynoodlebar.core.domain.Team;
import com.yummynoodlebar.events.orders.*;
import com.yummynoodlebar.persistence.services.TeamPersistenceService;

import java.util.Date;
import java.util.UUID;

public class TeamEventHandler implements TeamService {

  private final TeamPersistenceService teamsPersistenceService;

  public TeamEventHandler(final TeamPersistenceService teamsPersistenceService) {
    this.teamsPersistenceService = teamsPersistenceService;
  }

  @Override
  public TeamCreatedEvent createTeam(CreateTeamEvent createTeamEvent) {

    //TODO, add validation of menu items
    //TODO, add team total calculation
    //TODO, add team time estimate calculation
	//TODO  Think transaction boundary. Team and TeamStatus should be atomic
    TeamCreatedEvent event = teamsPersistenceService.createTeam(createTeamEvent);

    //TODO, where should this go?
    TeamStatusEvent teamStatusEvent = teamsPersistenceService.setTeamStatus(
            new SetTeamStatusEvent(event.getNewTeamKey(), new TeamStatusDetails(event.getNewTeamKey(),
            UUID.randomUUID(), new Date(), "Team Created")));

    return event;
  }

  @Override
  public AllTeamsEvent requestAllTeams(RequestAllTeamsEvent requestAllCurrentTeamsEvent) {
    return teamsPersistenceService.requestAllTeams(requestAllCurrentTeamsEvent);
  }

  @Override
  public TeamDetailsEvent requestTeamDetails(RequestTeamDetailsEvent requestTeamDetailsEvent) {
    return teamsPersistenceService.requestTeamDetails(requestTeamDetailsEvent);
  }

  @Override
  public TeamUpdatedEvent setTeamPayment(SetTeamPaymentEvent setTeamPaymentEvent) {
    return teamsPersistenceService.setTeamPayment(setTeamPaymentEvent);
  }

  @Override
  public TeamDeletedEvent deleteTeam(DeleteTeamEvent deleteTeamEvent) {

    TeamDetailsEvent teamDetailsEvent = teamsPersistenceService.requestTeamDetails(new RequestTeamDetailsEvent(deleteTeamEvent.getKey()));

    if (!teamDetailsEvent.isEntityFound()) {
      return TeamDeletedEvent.notFound(deleteTeamEvent.getKey());
    }

    Team team = Team.fromTeamDetails(teamDetailsEvent.getTeamDetails());

    if (!team.canBeDeleted()) {
      return TeamDeletedEvent.deletionForbidden(deleteTeamEvent.getKey(), team.toTeamDetails());
    }

    teamsPersistenceService.deleteTeam(deleteTeamEvent);

    return new TeamDeletedEvent(deleteTeamEvent.getKey(), team.toTeamDetails());
  }

  @Override
  public TeamStatusEvent requestTeamStatus(RequestTeamStatusEvent requestTeamDetailsEvent) {
    return teamsPersistenceService.requestTeamStatus(requestTeamDetailsEvent);
  }
}
