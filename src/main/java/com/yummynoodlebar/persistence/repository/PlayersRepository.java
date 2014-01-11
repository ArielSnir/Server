package com.yummynoodlebar.persistence.repository;

import com.yummynoodlebar.persistence.domain.Player;

import java.util.UUID;

public interface PlayersRepository {

  Player save(Player player);

  void delete(UUID key);

  Player findById(UUID key);

  Iterable<Player> findAll();
}
