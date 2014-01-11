package com.yummynoodlebar.persistence.repository;

import com.yummynoodlebar.persistence.domain.PlayerStatus;

import java.util.List;
import java.util.UUID;

public interface PlayerStatusRepository {

  PlayerStatus save(PlayerStatus orderStatus);

  void delete(UUID key);

  PlayerStatus findLatestById(UUID key);

  List<PlayerStatus> findAll();
}
