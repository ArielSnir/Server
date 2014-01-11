package com.yummynoodlebar.persistence.services;

import com.yummynoodlebar.events.orders.*;

public interface PlayerPersistenceService {

  public AllPlayersEvent requestAllPlayers(RequestAllPlayersEvent requestAllCurrentPlayersEvent);

  public PlayerDetailsEvent requestPlayerDetails(RequestPlayerDetailsEvent requestPlayerDetailsEvent);

  public PlayerStatusEvent requestPlayerStatus(RequestPlayerStatusEvent requestPlayerStatusEvent);

  public PlayerCreatedEvent createPlayer(CreatePlayerEvent event);

  public PlayerStatusEvent setPlayerStatus(SetPlayerStatusEvent event);

  public PlayerUpdatedEvent setPlayerPayment(SetPlayerPaymentEvent setPlayerPaymentEvent);

  public PlayerDeletedEvent deletePlayer(DeletePlayerEvent deletePlayerEvent);

}
