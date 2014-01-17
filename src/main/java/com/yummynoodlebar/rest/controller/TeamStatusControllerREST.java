package com.yummynoodlebar.rest.controller;

import com.yummynoodlebar.events.orders.TeamStatusEvent;
import com.yummynoodlebar.events.orders.RequestTeamStatusEvent;
import com.yummynoodlebar.core.services.TeamService;
import com.yummynoodlebar.rest.domain.TeamStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@RequestMapping("/aggregators/teams/{id}/status")
public class TeamStatusControllerREST {

  @Autowired
  private TeamService teamService;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<TeamStatus> getTeamStatus(@PathVariable String id) {

    TeamStatusEvent teamStatusEvent = teamService.requestTeamStatus(new RequestTeamStatusEvent(UUID.fromString(id)));

    if (!teamStatusEvent.isEntityFound()) {
      return new ResponseEntity<TeamStatus>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<TeamStatus>(
            TeamStatus.fromTeamStatusDetails(
                    teamStatusEvent.getKey(),
                    teamStatusEvent.getTeamStatus()),
            HttpStatus.OK);
  }
}
