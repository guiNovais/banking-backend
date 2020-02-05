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
	public void operation(@RequestBody OperationDTO body) {

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
		Client client = findClient(cardNumber);
		validateActivationCode(client, activationCode);
		client.setActivated(true);
		clientRepository.save(client);
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
