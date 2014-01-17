package com.yummynoodlebar.config;

import java.util.HashMap;
import java.util.UUID;

import com.yummynoodlebar.core.services.MenuEventHandler;
import com.yummynoodlebar.core.services.MenuService;
import com.yummynoodlebar.core.services.OrderEventHandler;
import com.yummynoodlebar.core.services.OrderService;
import com.yummynoodlebar.core.services.PlayerEventHandler;
import com.yummynoodlebar.core.services.PlayerService;
import com.yummynoodlebar.core.services.TeamEventHandler;
import com.yummynoodlebar.core.services.TeamService;
import com.yummynoodlebar.persistence.domain.Order;
import com.yummynoodlebar.persistence.repository.OrdersMemoryRepository;
import com.yummynoodlebar.persistence.repository.OrdersRepository;
import com.yummynoodlebar.persistence.services.MenuPersistenceService;
import com.yummynoodlebar.persistence.services.OrderPersistenceService;
import com.yummynoodlebar.persistence.services.PlayerPersistenceService;
import com.yummynoodlebar.persistence.services.TeamPersistenceService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {
	@Bean
	public MenuService menuService(MenuPersistenceService menuPersistenceService) {
		return new MenuEventHandler(menuPersistenceService);
	}
  @Bean
  public OrderService orderService(OrderPersistenceService orderPersistenceService) {
    return new OrderEventHandler(orderPersistenceService);
  }
  
  @Bean
  public PlayerService playerService(PlayerPersistenceService playerPersistenceService) {
    return new PlayerEventHandler(playerPersistenceService);
  }
  
  @Bean
  public TeamService teamService(TeamPersistenceService teamPersistenceService) {
    return new TeamEventHandler(teamPersistenceService);
  }
  
  @Bean
  public OrdersRepository createRepo() {
    return new OrdersMemoryRepository(new HashMap<UUID, Order>());
  }
}
