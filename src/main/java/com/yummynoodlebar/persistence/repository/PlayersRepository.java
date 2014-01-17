package com.yummynoodlebar.persistence.repository;

import com.yummynoodlebar.persistence.domain.Player;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface PlayersRepository extends CrudRepository<Player, String> {

/*  Player save(Player player);

  void delete(UUID key);

  Player findById(UUID key);

  Iterable<Player> findAll();*/
}
