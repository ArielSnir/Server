package com.yummynoodlebar.rest.controller;

import com.yummynoodlebar.events.orders.*;
import com.yummynoodlebar.core.services.PlayerService;
import com.yummynoodlebar.rest.domain.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/aggregators/players")
public class PlayerQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(PlayerQueriesController.class);

    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<Player>();
        for (PlayerDetails detail : playerService.requestAllPlayers(new RequestAllPlayersEvent()).getPlayersDetails()) {
            players.add(Player.fromPlayerDetails(detail));
        }
        return players;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Player> viewPlayer(@PathVariable String id) {

        PlayerDetailsEvent details = playerService.requestPlayerDetails(new RequestPlayerDetailsEvent(UUID.fromString(id)));

        if (!details.isEntityFound()) {
            return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
        }

        Player player = Player.fromPlayerDetails(details.getPlayerDetails());

        return new ResponseEntity<Player>(player, HttpStatus.OK);
    }
}
