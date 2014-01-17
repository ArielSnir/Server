package com.yummynoodlebar.persistence.services;

import com.yummynoodlebar.events.orders.*;
import com.yummynoodlebar.persistence.domain.Team;
import com.yummynoodlebar.persistence.domain.TeamStatus;
import com.yummynoodlebar.persistence.repository.TeamStatusRepository;
import com.yummynoodlebar.persistence.repository.TeamsRepository;

import java.util.ArrayList;
import java.util.List;

public class TeamPersistenceEventHandler implements TeamPersistenceService {

  private final TeamsRepository teamRepository;
  private final TeamStatusRepository teamStatusRepository;

  public TeamPersistenceEventHandler(
      final TeamsRepository teamRepository,
      final TeamStatusRepository teamStatusRepository) {
    this.teamRepository = teamRepository;
    this.teamStatusRepository = teamStatusRepository;
  }

  @Override
  public TeamStatusEvent setTeamStatus(SetTeamStatusEvent event) {

    TeamStatus status = TeamStatus.fromStatusDetails(event.getTeamStatus());

    status = teamStatusRepository.save(status);

    return new TeamStatusEvent(status.getId(), status.toStatusDetails());
  }

  @Override
  public TeamCreatedEvent createTeam(CreateTeamEvent createTeamEvent) {
    Team team = Team.fromTeamDetails(createTeamEvent.getDetails());

    team = teamRepository.save(team);

    return new TeamCreatedEvent(team.getKey(), team.toTeamDetails());
  }

  @Override
  public AllTeamsEvent requestAllTeams(RequestAllTeamsEvent requestAllCurrentTeamsEvent) {
    List<TeamDetails> generatedDetails = new ArrayList<TeamDetails>();
    for (Team team : teamRepository.findAll()) {
      generatedDetails.add(team.toTeamDetails());
    }
    return new AllTeamsEvent(generatedDetails);
  }

  @Override
  public TeamDetailsEvent requestTeamDetails(RequestTeamDetailsEvent requestTeamDetailsEvent) {

    //Team team = teamRepository.findById(requestTeamDetailsEvent.getKey());
	  
	  //TODO: consider replace the using of UUID with something else for example string.
	  Team team = teamRepository.findOne(requestTeamDetailsEvent.getKey().toString());

    if (team == null) {
      return TeamDetailsEvent.notFound(requestTeamDetailsEvent.getKey());
    }

    return new TeamDetailsEvent(
            requestTeamDetailsEvent.getKey(),
            team.toTeamDetails());
  }

  @Override
  public TeamUpdatedEvent setTeamPayment(SetTeamPaymentEvent setTeamPaymentEvent) {
    //Team team = teamRepository.findById(setTeamPaymentEvent.getKey());
	  Team team = teamRepository.findOne(setTeamPaymentEvent.getKey().toString());

    if(team == null) {
      return TeamUpdatedEvent.notFound(setTeamPaymentEvent.getKey());
    }

    //TODO, handling payment details...

    return new TeamUpdatedEvent(team.getKey(), team.toTeamDetails());
  }

  @Override
  public TeamDeletedEvent deleteTeam(DeleteTeamEvent deleteTeamEvent) {

    //Team team = teamRepository.findById(deleteTeamEvent.getKey());
	  Team team = teamRepository.findOne(deleteTeamEvent.getKey().toString());

    if (team == null) {
      return TeamDeletedEvent.notFound(deleteTeamEvent.getKey());
    }

    //teamRepository.delete(deleteTeamEvent.getKey());
    teamRepository.delete(deleteTeamEvent.getKey().toString());

    return new TeamDeletedEvent(deleteTeamEvent.getKey(), team.toTeamDetails());
  }

  @Override
  public TeamStatusEvent requestTeamStatus(RequestTeamStatusEvent requestTeamDetailsEvent) {
    
    TeamStatus teamStatus = teamStatusRepository.findLatestById(requestTeamDetailsEvent.getKey());

    if (teamStatus == null) {
      return TeamStatusEvent.notFound(requestTeamDetailsEvent.getKey());
    }

    return new TeamStatusEvent(requestTeamDetailsEvent.getKey(), teamStatus.toStatusDetails());
  }
}
