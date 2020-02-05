package com.example.banking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@Autowired
	ClientRepository clientRepository;
	
	@PostMapping("/clients/card/")
	public Client changeCardStatus(@RequestBody Client client) {
		Client retrivedClient = clientRepository.findByCardCode(client.getCardCode());
		retrivedClient.setActivated(client.getActivated());
		clientRepository.save(retrivedClient);
		
		return retrivedClient;
	}
}
