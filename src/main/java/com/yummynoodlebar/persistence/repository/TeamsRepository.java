package com.yummynoodlebar.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.yummynoodlebar.persistence.domain.Team;

public interface TeamsRepository extends CrudRepository<Team, String> {

/*  Team save(Team team);

  void delete(UUID key);

  Team findById(UUID key);

  Iterable<Team> findAll();*/
}
