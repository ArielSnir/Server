package com.yummynoodlebar.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import com.yummynoodlebar.persistence.repository.MenuItemRepository;
import com.yummynoodlebar.persistence.repository.PlayersRepository;
import com.yummynoodlebar.persistence.repository.TeamsRepository;

@Configuration
@EnableMongoRepositories(basePackages = "com.yummynoodlebar.persistence.repository",
      includeFilters = @ComponentScan.Filter(value = {TeamsRepository.class,PlayersRepository.class,MenuItemRepository.class}, type = FilterType.ASSIGNABLE_TYPE))
public class MongoConfiguration {

 /* public @Bean
  MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException {
    return new MongoTemplate(mongo, "yummynoodle");
  }

  public @Bean Mongo mongo() throws UnknownHostException {
	  MongoOperations mongo;
	  final String uri = "mongodb://varsaty:Giraffe1@ds039487.mongolab.com:39487/varsaty";
	  //mongo = new MongoTemplate(new SimpleMongoDbFactory(new MongoURI(uri)));
	  //mongo.dropCollection("menu");
    //return new Mongo("localhost");
	  return new Mongo(new MongoURI(uri));
  }*/
	

/*	  public @Bean
	  MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException {
	    return new MongoTemplate(mongo, "yummynoodle");
	  }

	  public @Bean Mongo mongo() throws UnknownHostException {
	    return new Mongo("localhost");
	  }*/
	
	  public @Bean
	  MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException {
		  UserCredentials userCredentials = new UserCredentials("varsaty", "Giraffe1");
		  
		return new MongoTemplate(mongo, "varsaty",userCredentials);
	  }

	  public @Bean Mongo mongo() throws UnknownHostException {
		  final String uri = "mongodb://varsaty:Giraffe1@ds039487.mongolab.com:39487/varsaty";
		  return new Mongo(new MongoURI(uri)/*"localhost"*/);
	  }
	
}
