package com.bankaccount.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaccount.persistence.entities.Account;
import com.bankaccount.persistence.entities.Client;
import com.bankaccount.persistence.repos.AccountRepository;
import com.bankaccount.persistence.repos.ClientRepository;

@Service
@Transactional
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AccountRepository accountRepository;
	public Client createClient(Client client) {
		// Do some data validation
		// Verify rules & policies 
		// if all rules & policies are OK then register else throw an BusinessException
		
		this.clientRepository.save(client);
		
		for(Account account : client.getAccounts()) {
			account.setClient(client);
			accountRepository.save(account);
		}
		
		return client;
	}
	
}
