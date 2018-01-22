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

import com.bankaccount.persistence.entities.Account;
import com.bankaccount.persistence.entities.Transaction;
import com.bankaccount.persistence.repos.AccountRepository;
import com.bankaccount.resource.exceptions.AccountNotFoundException;
import com.bankaccount.resource.exceptions.FundsException;
import com.bankaccount.service.account.AccountService;
import com.bankaccount.service.exception.InsufficientFundsException;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value="accountResource", description="Operations on the account resource")
/**
 * This class exposes the Account Resource
 * 
 * @author wabdeljaouad
 *
 */
public class AccountResource {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * Retrieve all the Accounts 
	 * @return the a List of all Accounts
	 */
	@GetMapping(path="/accounts")
	public List<Account> retrieveAllAccounts(){
		List<Account> accounts = accountRepository.findAll();
		
		return accounts;
	}
	
	/**
	 * Retrieve an Account 
	 * @param the id of the account 
	 * @return the account with the given id
	 */
	@GetMapping(path="/accounts/{id}")
	public Account retrieveAccountById(@PathVariable Long id) {
		Optional<Account> optionalAccount = accountRepository.findById(id);
		if(!optionalAccount.isPresent()) {
			throw new AccountNotFoundException("id " + id);
		}
		
		return optionalAccount.get();
	}
	
	/**
	 * add an Account 
	 * @param an account 
	 * @return the Uri of the new Account
	 */
	@PostMapping(path="/accounts")
	public ResponseEntity<Object> saveAccount(@RequestBody Account account){
		Account savedAccount = this.accountRepository.save(account);
		log.info("ClientResource : saveAccount :  {} ", savedAccount);
		URI location =		
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAccount.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * Retrieve all account transaction's  
	 * @param the id of the account 
	 * @return the list of the account transaction's 
	 */
	@GetMapping(path="/accounts/{id}/transactions")
	public List<Transaction> retrieveAccountTransactions(@PathVariable Long id){
		Optional<Account> optionalAccount = accountRepository.findById(id);
		if(!optionalAccount.isPresent()) {
			throw new AccountNotFoundException("id " + id);
		}
		return optionalAccount.get().getTransactions();
	}
	
	
	/**
	 * Retrieve all account transaction's  
	 * @param the id of the account 
	 * @return the list of the account transaction's 
	 */
	@PostMapping(path="/accounts/{id}/transactions/execute")
	public ResponseEntity<Object> executeWithdrawOrDepositAccountTransactions(@PathVariable Long id, @RequestBody Transaction transaction) {
		Optional<Account> optionalAccount = this.accountRepository.findById(id);
		if (!optionalAccount.isPresent()) {
			throw new AccountNotFoundException("id " + id);
		}
		
		
		Account accountResult;
		try {
			accountResult = this.accountService.executeAccountTransaction(optionalAccount.get(), transaction);
		} catch (InsufficientFundsException e) {
			throw new FundsException("101","Insuffisant funds");
		}
		
		return ResponseEntity.ok(accountResult);
	}
	
	
}
