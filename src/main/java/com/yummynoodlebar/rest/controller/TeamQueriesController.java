package com.yummynoodlebar.rest.controller;

import com.yummynoodlebar.events.orders.*;
import com.yummynoodlebar.core.services.TeamService;
import com.yummynoodlebar.rest.domain.Team;
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
@RequestMapping("/aggregators/teams")
public class TeamQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(TeamQueriesController.class);

    @Autowired
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<Team>();
        for (TeamDetails detail : teamService.requestAllTeams(new RequestAllTeamsEvent()).getTeamsDetails()) {
            teams.add(Team.fromTeamDetails(detail));
        }
        return teams;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Team> viewTeam(@PathVariable String id) {

        TeamDetailsEvent details = teamService.requestTeamDetails(new RequestTeamDetailsEvent(UUID.fromString(id)));

        if (!details.isEntityFound()) {
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }

        Team team = Team.fromTeamDetails(details.getTeamDetails());

        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }
}
