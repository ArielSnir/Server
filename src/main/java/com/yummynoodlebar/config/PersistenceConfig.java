package com.yummynoodlebar.config;

import com.yummynoodlebar.persistence.domain.MenuItem;
import com.yummynoodlebar.persistence.domain.Order;
import com.yummynoodlebar.persistence.domain.Player;
import com.yummynoodlebar.persistence.repository.*;
import com.yummynoodlebar.persistence.services.MenuPersistenceEventHandler;
import com.yummynoodlebar.persistence.services.MenuPersistenceService;
import com.yummynoodlebar.persistence.services.OrderPersistenceEventHandler;
import com.yummynoodlebar.persistence.services.OrderPersistenceService;
import com.yummynoodlebar.persistence.services.PlayerPersistenceEventHandler;
import com.yummynoodlebar.persistence.services.PlayerPersistenceService;
import com.yummynoodlebar.persistence.services.TeamPersistenceEventHandler;
import com.yummynoodlebar.persistence.services.TeamPersistenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@ContextConfiguration(classes = {MongoConfiguration.class})
public class PersistenceConfig {

	
	@Autowired
	PlayersRepository playersRepository;
	
	@Autowired
	TeamsRepository teamsRepository;
	
/*  @Autowired
  MenuItemRepository menuItemRepository;	

  @Autowired
  MongoOperations mongo;*/
  
  @Bean
  public OrdersRepository orderRepository() {
    return new OrdersMemoryRepository(new HashMap<UUID, Order>());
  }
  
/*  @Bean
  public PlayersRepository playerRepository() {
    return new PlayersMemoryRepository(new HashMap<UUID, Player>());
  }*/
  
  @Bean
  public OrderStatusRepository orderStatusRepository() {
    return new OrderStatusMemoryRepository();
  }  
  
  @Bean
  public PlayerStatusRepository playerStatusRepository() {
	    return new PlayerStatusMemoryRepository();
	  }
  @Bean
  public OrderPersistenceService ordersPersistenceService() {
    return new OrderPersistenceEventHandler(orderRepository(), orderStatusRepository());
  }

  @Bean
  public PlayerPersistenceService playersPersistenceService() {
    return new PlayerPersistenceEventHandler(playersRepository, playerStatusRepository());
  }
  
  @Bean
  public TeamStatusRepository teamStatusRepository() {
	    return new TeamStatusMemoryRepository();
	  }
  
  @Bean
  public TeamPersistenceService teamsPersistenceService() {
    return new TeamPersistenceEventHandler(teamsRepository, teamStatusRepository());
  }

	@Bean
	public MenuItemRepository menuItemRepository() {
		return new MenuItemMemoryRepository(defaultMenu());
		//return new MenuItemRepositoryImpl();
		//menuItemRepository.save(menuItem("YM1", new BigDecimal("1.99"), 11, "Yummy Noodles"));
		//return menuItemRepository;
	}

	@Bean
	public MenuPersistenceService menuPersistenceService(MenuItemRepository menuItemRepository) {
		return new MenuPersistenceEventHandler(menuItemRepository);
	}
	
	private Map<String, MenuItem> defaultMenu() {
		Map<String, MenuItem> items = new HashMap<String, MenuItem>();
		items.put("YM1", menuItem("YM1", new BigDecimal("1.99"), 11, "Yummy Noodles"));
		items.put("YM2", menuItem("YM2", new BigDecimal("2.99"), 12, "Special Yummy Noodles"));
		items.put("YM3", menuItem("YM3", new BigDecimal("3.99"), 13, "Low cal Yummy Noodles"));
		return items;
	}

	private MenuItem menuItem(String id, BigDecimal cost, int minutesToPrepare, String name) {
		MenuItem item = new MenuItem();
		item.setId(id);
		item.setCost(cost);
		item.setMinutesToPrepare(minutesToPrepare);
		item.setName(name);
		return item;
	}

}
