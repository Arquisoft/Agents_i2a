package controllers;

import domain.Agent;
import domain.AgentInfo;
import domain.AgentInfoAdapter;
import domain.AgentLoginData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import services.AgentsService;

/**
 * Created by Nicolás on 17/02/2017. Class that handles the login data response.
 * Access the service layer and recovers the user Modified by Marcos on
 * 17/02/2018
 */
@RestController
public class UserResponseAction {
	private final AgentsService part;

	@Autowired
	UserResponseAction(AgentsService part) {
		this.part = part;
	}

	@RequestMapping(value="/checkAgent", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AgentInfo> checkAgent(@RequestBody AgentLoginData info) {
		System.out.println("Entra");
		Agent user = part.getAgent(info.getLogin(), info.getPassword(),
				info.getKind());
		AgentInfoAdapter data = new AgentInfoAdapter(user);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(data.userToInfo(), HttpStatus.OK);
		}
	}
}
