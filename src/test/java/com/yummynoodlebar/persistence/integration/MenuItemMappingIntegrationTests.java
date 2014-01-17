package com.yummynoodlebar.persistence.integration;


import java.net.URI;

import com.mongodb.Mongo;
import com.mongodb.MongoURI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import static com.yummynoodlebar.persistence.domain.fixture.MongoAssertions.usingMongo;
import static com.yummynoodlebar.persistence.domain.fixture.PersistenceFixture.standardItem;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {CoreConfig.class, MVCConfig.class})
// {!begin top}
public class MenuItemMappingIntegrationTests {

  MongoOperations mongo;
  private String uri = "mongodb://varsaty:Giraffe1@ds039487.mongolab.com:39487/varsaty";

  @Before
  public void setup() throws Exception {
    /*mongo = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(new MongoURI(uri)), "yummynoodle"));
*/
	 // mongo = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(new MongoURI(uri)),"yummynoodle"));
	  mongo = new MongoTemplate(new SimpleMongoDbFactory(new MongoURI(uri)));
    mongo.dropCollection("menu");
  }

  @After
  public void teardown() {
    //mongo.dropCollection("menu");
  }

  @Test
  public void thatItemIsInsertedIntoCollectionHasCorrectIndexes() throws Exception {

    mongo.insert(standardItem());

    assertEquals(1, mongo.getCollection("menu").count());

    assertTrue(usingMongo(mongo).collection("menu").hasIndexOn("_id"));
    assertTrue(usingMongo(mongo).collection("menu").hasIndexOn("itemName"));
  }

  @Test
  public void thatItemCustomMappingWorks() throws Exception {
    mongo.insert(standardItem());

    assertTrue(usingMongo(mongo).collection("menu").first().hasField("itemName"));
  }
}
// {!end top}
