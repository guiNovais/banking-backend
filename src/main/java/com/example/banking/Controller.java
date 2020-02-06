package com.example.banking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@Log
@RestController
public class Controller {

	@Autowired
	ClientRepository clientRepository;

	@PostMapping("/card")
	public void operation(@RequestBody OperationDTO body) {
		log.info(body.toString());

		switch (body.getAction()) {
		case ACTIVATE:
			activateCard(body.getCardNumber(), body.getActivationCode());
			break;
		case CANCEL:
			cancelCard(body.getCardNumber());
			break;
		default:
			throw new RuntimeException();
		}

	}

	private void cancelCard(String cardNumber) {
		Client client = findClient(cardNumber);
		client.setActivated(false);
		clientRepository.save(client);

	}

	private void activateCard(String cardNumber, String activationCode) {
		log.info("activateCard(String, String) invoked");
		log.info(cardNumber);
		log.info(activationCode);
		Client client = findClient(cardNumber);
		validateActivationCode(client, activationCode);
		client.setActivated(true);
		clientRepository.save(client);
	}

	private void validateActivationCode(Client client, String activationCode) {
		log.info("validateActivationCode(Client, String), invoked");
		log.info(client.toString());
		log.info(activationCode);
		if (!client.getActivationCode().equals(activationCode)) {
			log.info(String.format("activationCode %s invalid!", activationCode));
			throw new ForbiddenException();
		}
	}

	private Client findClient(String cardNumber) {
		log.info("findClient(String) invoked");
		Client retrivedClient = clientRepository.findByCardNumber(cardNumber);

		if (retrivedClient == null) {
			log.info(String.format("Client with cardNumber %s not found", cardNumber));
			throw new NotFoundException();
		}

		return retrivedClient;

	}
}
