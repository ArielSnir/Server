package com.yummynoodlebar.rest.controller;

import com.yummynoodlebar.events.orders.CreatePlayerEvent;
import com.yummynoodlebar.events.orders.DeletePlayerEvent;
import com.yummynoodlebar.events.orders.PlayerCreatedEvent;
import com.yummynoodlebar.events.orders.PlayerDeletedEvent;
import com.yummynoodlebar.core.services.PlayerService;
import com.yummynoodlebar.rest.domain.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Controller
@RequestMapping("/aggregators/players")
public class PlayerCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(PlayerCommandsController.class);

    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Player> createPlayer(@RequestBody Player player, UriComponentsBuilder builder) {

        PlayerCreatedEvent playerCreated = playerService.createPlayer(new CreatePlayerEvent(player.toPlayerDetails()));

        Player newPlayer = Player.fromPlayerDetails(playerCreated.getDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/aggregators/players/{id}")
                        .buildAndExpand(playerCreated.getNewPlayerKey().toString()).toUri());

        return new ResponseEntity<Player>(newPlayer, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Player> cancelPlayer(@PathVariable String id) {

        PlayerDeletedEvent playerDeleted = playerService.deletePlayer(new DeletePlayerEvent(UUID.fromString(id)));

        if (!playerDeleted.isEntityFound()) {
            return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
        }

        Player player = Player.fromPlayerDetails(playerDeleted.getDetails());

        if (playerDeleted.isDeletionCompleted()) {
            return new ResponseEntity<Player>(player, HttpStatus.OK);
        }

        return new ResponseEntity<Player>(player, HttpStatus.FORBIDDEN);
    }
}
