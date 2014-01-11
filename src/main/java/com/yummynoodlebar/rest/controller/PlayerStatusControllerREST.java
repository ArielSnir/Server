package com.yummynoodlebar.rest.controller;

import com.yummynoodlebar.events.orders.PlayerStatusEvent;
import com.yummynoodlebar.events.orders.RequestPlayerStatusEvent;
import com.yummynoodlebar.core.services.PlayerService;
import com.yummynoodlebar.rest.domain.PlayerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@RequestMapping("/aggregators/players/{id}/status")
public class PlayerStatusControllerREST {

  @Autowired
  private PlayerService playerService;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<PlayerStatus> getPlayerStatus(@PathVariable String id) {

    PlayerStatusEvent playerStatusEvent = playerService.requestPlayerStatus(new RequestPlayerStatusEvent(UUID.fromString(id)));

    if (!playerStatusEvent.isEntityFound()) {
      return new ResponseEntity<PlayerStatus>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<PlayerStatus>(
            PlayerStatus.fromPlayerStatusDetails(
                    playerStatusEvent.getKey(),
                    playerStatusEvent.getPlayerStatus()),
            HttpStatus.OK);
  }
}
