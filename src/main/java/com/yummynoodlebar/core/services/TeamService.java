package com.yummynoodlebar.core.services;

import com.yummynoodlebar.events.orders.*;

//TODOCUMENT THis is an event driven service.
// Used to interact with the core domain.
//All methods are guaranteed to return something, null will never be returned.
public interface TeamService {

  public AllTeamsEvent requestAllTeams(RequestAllTeamsEvent requestAllCurrentTeamsEvent);

  public TeamDetailsEvent requestTeamDetails(RequestTeamDetailsEvent requestTeamDetailsEvent);

  public TeamStatusEvent requestTeamStatus(RequestTeamStatusEvent requestTeamStatusEvent);

  public TeamCreatedEvent createTeam(CreateTeamEvent event);

  public TeamUpdatedEvent setTeamPayment(SetTeamPaymentEvent setTeamPaymentEvent);

  public TeamDeletedEvent deleteTeam(DeleteTeamEvent deleteTeamEvent);
}
