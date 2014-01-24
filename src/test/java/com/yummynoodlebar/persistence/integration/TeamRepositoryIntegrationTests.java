package com.yummynoodlebar.persistence.integration;


import com.yummynoodlebar.config.MongoConfiguration;
import com.yummynoodlebar.persistence.domain.Team;
import com.yummynoodlebar.persistence.repository.PlayersRepository;
import com.yummynoodlebar.persistence.repository.TeamsRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.yummynoodlebar.persistence.domain.fixture.PersistenceFixture.standardTeam;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfiguration.class})
public class TeamRepositoryIntegrationTests {

  @Autowired
  TeamsRepository teamsRepository;
  @Autowired
  PlayersRepository playersRepository;

  @Autowired
  MongoOperations mongo;



  @Before
  public void setup() throws Exception {
    mongo.dropCollection("team");
  }

  @After
  public void teardown() {
    //mongo.dropCollection("team");
  }

  @Test
  public void thatItemIsInsertedIntoRepoWorks() throws Exception {

    assertEquals(0, mongo.getCollection("team").count());

    teamsRepository.save(standardTeam());
    playersRepository.save(standardTeam().getPlayers());
    
    assertEquals(1, mongo.getCollection("team").count());
  }

}
	