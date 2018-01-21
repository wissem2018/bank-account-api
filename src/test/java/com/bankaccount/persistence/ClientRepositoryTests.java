package com.bankaccount.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bankaccount.persistence.entities.Address;
import com.bankaccount.persistence.entities.Bank;
import com.bankaccount.persistence.entities.Client;
import com.bankaccount.persistence.entities.GenderType;
import com.bankaccount.persistence.repos.BankRepository;
import com.bankaccount.persistence.repos.ClientRepository;
import com.bankaccount.persistence.repos.GenderTypeRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ClientRepositoryTests {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private GenderTypeRepository genderTypeRepository;
	
	@Autowired
	private BankRepository bankRepository;
	
	@Test
	public void findClientById() {
		Optional<Client> optionalClient =  clientRepository.findById(10000L);
		log.info("The client with the id = 1 : {} ", optionalClient.get());
	}
	
	@Test
	public void saveNewClientWithNewAddress() {
		// Get the gender type from the referential 
		Optional<GenderType> optionalGenderType = genderTypeRepository.findById(1L);
		// Get the bank 
		Optional<Bank> optionalBank = bankRepository.findById(1L);
			
		Client client = new Client("3304849857", "Marcel", "Martin", "0766654758", LocalDate.of(1980, 10, 2), "marcel.martin@yahoo.com",optionalGenderType.get() , "No more informations" );
		client.setBank(optionalBank.get());
		// Adding the client address
		Address address = new Address("17 avenue Rome", "75009", "Paris", "France", "No comment");
		client.setAddress(address);
		
		Client savedClient =  clientRepository.save(client);
		log.info("The new Client is : {} ", savedClient);
		assertNotNull(savedClient.getId());
	}
	
	
	@Test
	public void saveNewClientWithNewAccount() {
		// Get the gender type from the referential 
		Optional<GenderType> optionalGenderType = genderTypeRepository.findById(1L);
		// Get the bank 
		Optional<Bank> optionalBank = bankRepository.findById(1L);
			
		Client client = new Client("3304849857", "Marcel", "Martin", "0766654758", LocalDate.of(1980, 10, 2), "marcel.martin@yahoo.com",optionalGenderType.get() , "No more informations" );
		client.setBank(optionalBank.get());
		// Adding the client address
		Address address = new Address("17 avenue Rome", "75009", "Paris", "France", "No comment");
		client.setAddress(address);
		
		Client savedClient =  clientRepository.save(client);
		log.info("The new Client is : {} ", savedClient);
		assertNotNull(savedClient.getId());
	}
	
	
	@Test
	public void updateClientEmail() {
		// Find the client with id 10000L 
		Optional<Client> optionalClient =  clientRepository.findById(10000L);
		
		// Update the email 
		if (optionalClient.isPresent()) {
			Client client = optionalClient.get();
			client.setEmail("joe.watson@gmail.com");
			clientRepository.save(client);
			log.info("FindClientById: id={}, result = {} ",1L,  client);
		
		}
		// Get the saved client  
		Optional<Client> optionalClient2 =  clientRepository.findById(10000L);
	
		assertEquals("joe.watson@gmail.com", optionalClient2.get().getEmail());
		
	}
	
}
