package com.yummynoodlebar.persistence.repository;

import com.yummynoodlebar.persistence.domain.PlayerStatus;

import java.util.*;

public class PlayerStatusMemoryRepository implements PlayerStatusRepository {

  private Map<UUID, PlayerStatus> playerStatuses = new HashMap<UUID, PlayerStatus>();

  @Override
  public PlayerStatus save(PlayerStatus player) {
    playerStatuses.put(player.getId(), player);
    return player;
  }

  @Override
  public void delete(UUID key) {
    playerStatuses.remove(key);
  }

  @Override
  public PlayerStatus findLatestById(UUID key) {
    for(PlayerStatus item: playerStatuses.values()) {
      if (item.getPlayerId().equals(key)) {
        return item;
      }
    }
    return null;
  }

  @Override
  public List<PlayerStatus> findAll() {
    return new ArrayList<PlayerStatus>(playerStatuses.values());
  }
}
