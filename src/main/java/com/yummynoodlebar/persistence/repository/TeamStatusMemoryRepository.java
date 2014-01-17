package com.yummynoodlebar.persistence.repository;

import com.yummynoodlebar.persistence.domain.TeamStatus;

import java.util.*;

public class TeamStatusMemoryRepository implements TeamStatusRepository {

  private Map<UUID, TeamStatus> teamStatuses = new HashMap<UUID, TeamStatus>();

  @Override
  public TeamStatus save(TeamStatus team) {
    teamStatuses.put(team.getId(), team);
    return team;
  }

  @Override
  public void delete(UUID key) {
    teamStatuses.remove(key);
  }

  @Override
  public TeamStatus findLatestById(UUID key) {
    for(TeamStatus item: teamStatuses.values()) {
      if (item.getTeamId().equals(key)) {
        return item;
      }
    }
    return null;
  }

  @Override
  public List<TeamStatus> findAll() {
    return new ArrayList<TeamStatus>(teamStatuses.values());
  }
}
