package com.yummynoodlebar.persistence.services;

import com.yummynoodlebar.events.orders.*;

public interface TeamPersistenceService {

  public AllTeamsEvent requestAllTeams(RequestAllTeamsEvent requestAllCurrentTeamsEvent);

  public TeamDetailsEvent requestTeamDetails(RequestTeamDetailsEvent requestTeamDetailsEvent);

  public TeamStatusEvent requestTeamStatus(RequestTeamStatusEvent requestTeamStatusEvent);

  public TeamCreatedEvent createTeam(CreateTeamEvent event);

  public TeamStatusEvent setTeamStatus(SetTeamStatusEvent event);

  public TeamUpdatedEvent setTeamPayment(SetTeamPaymentEvent setTeamPaymentEvent);

  public TeamDeletedEvent deleteTeam(DeleteTeamEvent deleteTeamEvent);

}
