package com.yummynoodlebar.core.services;

import com.yummynoodlebar.core.domain.Player;
import com.yummynoodlebar.events.orders.*;
import com.yummynoodlebar.persistence.services.PlayerPersistenceService;

import java.util.Date;
import java.util.UUID;

public class PlayerEventHandler implements PlayerService {

  private final PlayerPersistenceService playersPersistenceService;

  public PlayerEventHandler(final PlayerPersistenceService playersPersistenceService) {
    this.playersPersistenceService = playersPersistenceService;
  }

  @Override
  public PlayerCreatedEvent createPlayer(CreatePlayerEvent createPlayerEvent) {

    //TODO, add validation of menu items
    //TODO, add player total calculation
    //TODO, add player time estimate calculation
	//TODO  Think transaction boundary. Player and PlayerStatus should be atomic
    PlayerCreatedEvent event = playersPersistenceService.createPlayer(createPlayerEvent);

    //TODO, where should this go?
    PlayerStatusEvent playerStatusEvent = playersPersistenceService.setPlayerStatus(
            new SetPlayerStatusEvent(event.getNewPlayerKey(), new PlayerStatusDetails(event.getNewPlayerKey(),
            UUID.randomUUID(), new Date(), "Player Created")));

    return event;
  }

  @Override
  public AllPlayersEvent requestAllPlayers(RequestAllPlayersEvent requestAllCurrentPlayersEvent) {
    return playersPersistenceService.requestAllPlayers(requestAllCurrentPlayersEvent);
  }

  @Override
  public PlayerDetailsEvent requestPlayerDetails(RequestPlayerDetailsEvent requestPlayerDetailsEvent) {
    return playersPersistenceService.requestPlayerDetails(requestPlayerDetailsEvent);
  }

  @Override
  public PlayerUpdatedEvent setPlayerPayment(SetPlayerPaymentEvent setPlayerPaymentEvent) {
    return playersPersistenceService.setPlayerPayment(setPlayerPaymentEvent);
  }

  @Override
  public PlayerDeletedEvent deletePlayer(DeletePlayerEvent deletePlayerEvent) {

    PlayerDetailsEvent playerDetailsEvent = playersPersistenceService.requestPlayerDetails(new RequestPlayerDetailsEvent(deletePlayerEvent.getKey()));

    if (!playerDetailsEvent.isEntityFound()) {
      return PlayerDeletedEvent.notFound(deletePlayerEvent.getKey());
    }

    Player player = Player.fromPlayerDetails(playerDetailsEvent.getPlayerDetails());

    if (!player.canBeDeleted()) {
      return PlayerDeletedEvent.deletionForbidden(deletePlayerEvent.getKey(), player.toPlayerDetails());
    }

    playersPersistenceService.deletePlayer(deletePlayerEvent);

    return new PlayerDeletedEvent(deletePlayerEvent.getKey(), player.toPlayerDetails());
  }

  @Override
  public PlayerStatusEvent requestPlayerStatus(RequestPlayerStatusEvent requestPlayerDetailsEvent) {
    return playersPersistenceService.requestPlayerStatus(requestPlayerDetailsEvent);
  }
}
