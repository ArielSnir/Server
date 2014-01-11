package com.yummynoodlebar.core.services;

import com.yummynoodlebar.events.orders.*;

//TODOCUMENT THis is an event driven service.
// Used to interact with the core domain.
//All methods are guaranteed to return something, null will never be returned.
public interface PlayerService {

  public AllPlayersEvent requestAllPlayers(RequestAllPlayersEvent requestAllCurrentPlayersEvent);

  public PlayerDetailsEvent requestPlayerDetails(RequestPlayerDetailsEvent requestPlayerDetailsEvent);

  public PlayerStatusEvent requestPlayerStatus(RequestPlayerStatusEvent requestPlayerStatusEvent);

  public PlayerCreatedEvent createPlayer(CreatePlayerEvent event);

  public PlayerUpdatedEvent setPlayerPayment(SetPlayerPaymentEvent setPlayerPaymentEvent);

  public PlayerDeletedEvent deletePlayer(DeletePlayerEvent deletePlayerEvent);
}
