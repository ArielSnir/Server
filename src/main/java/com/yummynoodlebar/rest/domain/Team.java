package com.yummynoodlebar.rest.domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import com.yummynoodlebar.events.orders.TeamDetails;
import com.yummynoodlebar.rest.controller.TeamQueriesController;
// {!begin import}
// {!end import}

//TODOCUMENT This is added so that we can do jaxb serialisation.
//this type of annotation is fine here, as this
//Team implementation is made for integration with things like this.

@XmlRootElement
// {!begin resourceSupport}
public class Team extends ResourceSupport implements Serializable {
	// {!end resourceSupport}

	private String name;

	private Date dateTimeOfSubmission;

	private Map<String, Integer> items;

	private UUID key;

	public Date getDateTimeOfSubmission() {
		return dateTimeOfSubmission;
	}

	public UUID getKey() {
		return key;
	}

	public Map<String, Integer> getItems() {
		return items;
	}

	public void setItems(Map<String, Integer> items) {
		if (items == null) {
			this.items = Collections.emptyMap();
		} else {
			this.items = Collections.unmodifiableMap(items);
		}
	}

	public void setDateTimeOfSubmission(Date dateTimeOfSubmission) {
		this.dateTimeOfSubmission = dateTimeOfSubmission;
	}

	public void setKey(UUID key) {
		this.key = key;
	}

	public TeamDetails toTeamDetails() {
		TeamDetails details = new TeamDetails();
		
		details.setName(name);
		details.setTeamItems(items);
		details.setKey(key);
		details.setDateTimeOfSubmission(dateTimeOfSubmission);

		return details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	// {!begin fromTeamDetails}
	public static Team fromTeamDetails(TeamDetails teamDetails) {
		Team team = new Team();

		team.name = teamDetails.getName();
		team.dateTimeOfSubmission = teamDetails.getDateTimeOfSubmission();
		team.key = teamDetails.getKey();
		team.setItems(teamDetails.getTeamItems());

		//TODOCUMENT.  Adding the library, the above extends ResourceSupport and
		//this section is all that is actually needed in our model to add hateoas support.


		//Much of the rest of the framework is helping deal with the blending of domains that happens in many spring apps
		//We have explicitly avoided that.
		// {!begin selfRel}
		team.add(linkTo(TeamQueriesController.class).slash(team.key).withSelfRel());
		// {!end selfRel}
		// {!begin status}
		team.add(linkTo(TeamQueriesController.class).slash(team.key).slash("status").withRel("Team Status"));
		// {!end status}
		team.add(linkTo(TeamQueriesController.class).slash(team.key).slash("paymentdetails").withRel("Payment Details"));

		return team;
	}
	// {!end fromTeamDetails}
}
