package com.bankaccount.resource.account;

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

import com.bankaccount.persistence.entities.Transaction;
import com.bankaccount.persistence.repos.TransactionRepository;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
/**
 * This class exposes the transaction resource
 * @author wabdeljaouad
 *
 */
@RestController
@Slf4j
@Api(value="transactionResource", description="Operations on the transaction resource")
public class TransactionResource {
	
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	/**
	 * Retrieve the list of all transactions
	 * @return List of all transactions
	 */
	@GetMapping(path="/transactions")
	public List<Transaction> retrieveAllTransactions(){
		List<Transaction> transactions = transactionRepository.findAll();
		
		return transactions;
	}
	
	/**
	 * Retrieve a transaction 
	 * @param id of the transaction
	 * @return
	 */
	@GetMapping(path="/transactions/{id}")
	public Transaction retrieveTransactionById(@PathVariable Long id){
		Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
		
		return optionalTransaction.get();
	}
	
	/**
	 * Add a transaction
	 * @param transaction
	 * @return uri for the new transaction
	 */
	@PostMapping(path="/transactions")
	public ResponseEntity<Object> saveTransaction(@RequestBody Transaction transaction){
		Transaction savedTransaction = this.transactionRepository.save(transaction);
		log.info("ClientResource : saveTransaction :  {} ", savedTransaction);
		URI location =		
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTransaction.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
