package com.yummynoodlebar.persistence.services;

import com.yummynoodlebar.events.orders.*;
import com.yummynoodlebar.persistence.domain.Player;
import com.yummynoodlebar.persistence.domain.PlayerStatus;
import com.yummynoodlebar.persistence.repository.PlayerStatusRepository;
import com.yummynoodlebar.persistence.repository.PlayersRepository;

import java.util.ArrayList;
import java.util.List;

public class PlayerPersistenceEventHandler implements PlayerPersistenceService {

  private final PlayersRepository playerRepository;
  private final PlayerStatusRepository playerStatusRepository;

  public PlayerPersistenceEventHandler(
      final PlayersRepository playerRepository,
      final PlayerStatusRepository playerStatusRepository) {
    this.playerRepository = playerRepository;
    this.playerStatusRepository = playerStatusRepository;
  }

  @Override
  public PlayerStatusEvent setPlayerStatus(SetPlayerStatusEvent event) {

    PlayerStatus status = PlayerStatus.fromStatusDetails(event.getPlayerStatus());

    status = playerStatusRepository.save(status);

    return new PlayerStatusEvent(status.getId(), status.toStatusDetails());
  }

  @Override
  public PlayerCreatedEvent createPlayer(CreatePlayerEvent createPlayerEvent) {
    Player player = Player.fromPlayerDetails(createPlayerEvent.getDetails());

    player = playerRepository.save(player);

    return new PlayerCreatedEvent(player.getKey(), player.toPlayerDetails());
  }

  @Override
  public AllPlayersEvent requestAllPlayers(RequestAllPlayersEvent requestAllCurrentPlayersEvent) {
    List<PlayerDetails> generatedDetails = new ArrayList<PlayerDetails>();
    for (Player player : playerRepository.findAll()) {
      generatedDetails.add(player.toPlayerDetails());
    }
    return new AllPlayersEvent(generatedDetails);
  }

  @Override
  public PlayerDetailsEvent requestPlayerDetails(RequestPlayerDetailsEvent requestPlayerDetailsEvent) {

    //Player player = playerRepository.findById(requestPlayerDetailsEvent.getKey());
	  
	  //TODO: consider replace the using of UUID with something else for example string.
	  Player player = playerRepository.findOne(requestPlayerDetailsEvent.getKey().toString());

    if (player == null) {
      return PlayerDetailsEvent.notFound(requestPlayerDetailsEvent.getKey());
    }

    return new PlayerDetailsEvent(
            requestPlayerDetailsEvent.getKey(),
            player.toPlayerDetails());
  }

  @Override
  public PlayerUpdatedEvent setPlayerPayment(SetPlayerPaymentEvent setPlayerPaymentEvent) {
    //Player player = playerRepository.findById(setPlayerPaymentEvent.getKey());
	  Player player = playerRepository.findOne(setPlayerPaymentEvent.getKey().toString());

    if(player == null) {
      return PlayerUpdatedEvent.notFound(setPlayerPaymentEvent.getKey());
    }

    //TODO, handling payment details...

    return new PlayerUpdatedEvent(player.getKey(), player.toPlayerDetails());
  }

  @Override
  public PlayerDeletedEvent deletePlayer(DeletePlayerEvent deletePlayerEvent) {

    //Player player = playerRepository.findById(deletePlayerEvent.getKey());
	  Player player = playerRepository.findOne(deletePlayerEvent.getKey().toString());

    if (player == null) {
      return PlayerDeletedEvent.notFound(deletePlayerEvent.getKey());
    }

    //playerRepository.delete(deletePlayerEvent.getKey());
    playerRepository.delete(deletePlayerEvent.getKey().toString());

    return new PlayerDeletedEvent(deletePlayerEvent.getKey(), player.toPlayerDetails());
  }

  @Override
  public PlayerStatusEvent requestPlayerStatus(RequestPlayerStatusEvent requestPlayerDetailsEvent) {
    
    PlayerStatus playerStatus = playerStatusRepository.findLatestById(requestPlayerDetailsEvent.getKey());

    if (playerStatus == null) {
      return PlayerStatusEvent.notFound(requestPlayerDetailsEvent.getKey());
    }

    return new PlayerStatusEvent(requestPlayerDetailsEvent.getKey(), playerStatus.toStatusDetails());
  }
}
