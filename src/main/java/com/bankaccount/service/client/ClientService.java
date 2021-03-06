package com.bankaccount.service.client;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaccount.persistence.entities.Client;
import com.bankaccount.persistence.repos.ClientRepository;

@Service
@Transactional
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client createClient(Client client) {
		// Do some data validation
		// Verify rules & policies 
		// if all rules & policies are OK then register else throw an BusinessException

		this.clientRepository.save(client);
		
		return client;
	}
	
}
