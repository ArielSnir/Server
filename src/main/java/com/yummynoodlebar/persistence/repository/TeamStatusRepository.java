package com.yummynoodlebar.persistence.repository;

import com.yummynoodlebar.persistence.domain.TeamStatus;

import java.util.List;
import java.util.UUID;

public interface TeamStatusRepository {

  TeamStatus save(TeamStatus orderStatus);

  void delete(UUID key);

  TeamStatus findLatestById(UUID key);

  List<TeamStatus> findAll();
}
