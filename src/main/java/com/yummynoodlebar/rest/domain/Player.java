package com.yummynoodlebar.rest.domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import com.yummynoodlebar.events.orders.PlayerDetails;
import com.yummynoodlebar.rest.controller.PlayerQueriesController;
// {!begin import}
// {!end import}

//TODOCUMENT This is added so that we can do jaxb serialisation.
//this type of annotation is fine here, as this
//Player implementation is made for integration with things like this.

@XmlRootElement
// {!begin resourceSupport}
public class Player extends ResourceSupport implements Serializable {
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

	public PlayerDetails toPlayerDetails() {
		PlayerDetails details = new PlayerDetails();
		
		details.setName(name);
		details.setPlayerItems(items);
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



	// {!begin fromPlayerDetails}
	public static Player fromPlayerDetails(PlayerDetails playerDetails) {
		Player player = new Player();

		player.name = playerDetails.getName();
		player.dateTimeOfSubmission = playerDetails.getDateTimeOfSubmission();
		player.key = playerDetails.getKey();
		player.setItems(playerDetails.getPlayerItems());

		//TODOCUMENT.  Adding the library, the above extends ResourceSupport and
		//this section is all that is actually needed in our model to add hateoas support.


		//Much of the rest of the framework is helping deal with the blending of domains that happens in many spring apps
		//We have explicitly avoided that.
		// {!begin selfRel}
		player.add(linkTo(PlayerQueriesController.class).slash(player.key).withSelfRel());
		// {!end selfRel}
		// {!begin status}
		player.add(linkTo(PlayerQueriesController.class).slash(player.key).slash("status").withRel("Player Status"));
		// {!end status}
		player.add(linkTo(PlayerQueriesController.class).slash(player.key).slash("paymentdetails").withRel("Payment Details"));

		return player;
	}
	// {!end fromPlayerDetails}
}
