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
	public Client card(@RequestBody String action, @RequestBody String cardNumber, @RequestBody String activationCode) {

		if (action.equals("activateCard")) {
			Client client = findClient(cardNumber);
			validateActivationCode(client, activationCode);
			client.setActivated(true);
			return clientRepository.save(client);
		}

		if(action.equals("cancelCard")){
			Client client = findClient(cardNumber);
			client.setActivated(false);
			return clientRepository.save(client);
		}

		throw new RuntimeException();
	}

	private void validateActivationCode(Client client, String activationCode) {
		if (!client.getActivationCode().equals(activationCode)) {
			throw new ForbiddenException();
		}
	}

	private Client findClient(String cardNumber) {
		Client retrivedClient = clientRepository.findByCardNumber(cardNumber);

		if (retrivedClient == null) {
			throw new NotFoundException();
		}

		return retrivedClient;

	}
}
