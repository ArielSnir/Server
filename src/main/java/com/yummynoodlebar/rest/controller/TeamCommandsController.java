package com.yummynoodlebar.rest.controller;

import com.yummynoodlebar.events.orders.CreatePlayerEvent;
import com.yummynoodlebar.events.orders.CreateTeamEvent;
import com.yummynoodlebar.events.orders.DeleteTeamEvent;
import com.yummynoodlebar.events.orders.TeamCreatedEvent;
import com.yummynoodlebar.events.orders.TeamDeletedEvent;
import com.yummynoodlebar.core.services.TeamService;
import com.yummynoodlebar.rest.domain.Team;
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
@RequestMapping("/aggregators/teams")
public class TeamCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(TeamCommandsController.class);

    @Autowired
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Team> createTeam(@RequestBody Team team, UriComponentsBuilder builder) {

        TeamCreatedEvent teamCreated = teamService.createTeam(new CreateTeamEvent(team.toTeamDetails()));

        Team newTeam = Team.fromTeamDetails(teamCreated.getDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/aggregators/teams/{id}")
                        .buildAndExpand(teamCreated.getNewTeamKey().toString()).toUri());

        return new ResponseEntity<Team>(newTeam, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Team> cancelTeam(@PathVariable String id) {

        TeamDeletedEvent teamDeleted = teamService.deleteTeam(new DeleteTeamEvent(UUID.fromString(id)));

        if (!teamDeleted.isEntityFound()) {
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }

        Team team = Team.fromTeamDetails(teamDeleted.getDetails());

        if (teamDeleted.isDeletionCompleted()) {
            return new ResponseEntity<Team>(team, HttpStatus.OK);
        }

        return new ResponseEntity<Team>(team, HttpStatus.FORBIDDEN);
    }
}
