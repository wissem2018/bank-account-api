package com.bankaccount.service.account;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bankaccount.persistence.entities.Account;
import com.bankaccount.persistence.entities.Transaction;
import com.bankaccount.service.exception.AccountException;
/**
 * This class implements methods that manage the account
 * @author wabdeljaouad
 *
 */
@Service
@Transactional
public class AccountService {
	
	
	/**
	 * This method execute an account transaction
	 * the transaction can be of type Withdraw (code = W ) or Deposit ( code = D )   
	 * @param account account before transaction
	 * @param transaction
	 * @return Account account after transaction
	 * @throws AccountException
	 */
	
	public Account processAccountTransaction (Account account, Transaction transaction ) throws AccountException {
		
		// Initialize the transaction balance with the account currentBalance		
		transaction.setBalance(account.getCurrentBalance());
		// Process the transaction 
		account.processTransaction(transaction);
		
		return account;
		
	}

}
