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
	public Client activateCard(@RequestBody String cardNumber) {
		Client retrivedClient = clientRepository.findByCardNumber(cardNumber);

		if(retrivedClient == null){
			throw new NotFoundException();
		}

		retrivedClient.setActivated(true);
		Client savedClient = clientRepository.save(retrivedClient);
		
		return savedClient;
	}
}
