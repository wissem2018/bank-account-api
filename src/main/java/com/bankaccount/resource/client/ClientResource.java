package com.bankaccount.resource.client;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bankaccount.persistence.entities.Client;
import com.bankaccount.persistence.repos.ClientRepository;
import com.bankaccount.service.client.ClientService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@Api(value="clientResource", description="Operations on the client resource")
public class ClientResource {
	
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;
	
	public ClientResource() {
		super();
	}
	@GetMapping(path="/clients")
	public List<Client> retrieveAllClients(){
		log.info("ClientResource : retrieveAllClients : clients  {} ", this.clientRepository.findAll());
		return this.clientRepository.findAll();
	}
	
	@GetMapping(path="/clients/{id}")
	public Client retrieveClientById(@PathVariable Long id){
		Optional<Client> optionalClient = this.clientRepository.findById(id);
		
		if(!optionalClient.isPresent()) {
			
		}
		log.info("ClientResource : retrieveClientById : client By id  {} ", optionalClient);
		return optionalClient.get();
	}
	
	@PostMapping(path="/clients")
	public ResponseEntity<Object> saveClient(@RequestBody Client client){
		Client savedClient = this.clientService.createClient(client);
		log.info("ClientResource : saveClient :  {} ", savedClient);
		URI location =		
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedClient.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
}
