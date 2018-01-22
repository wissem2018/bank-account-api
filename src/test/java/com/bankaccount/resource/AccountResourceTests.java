package com.bankaccount.resource;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.bankaccount.BankAccountApiApplication;
import com.bankaccount.DataFactory;
import com.bankaccount.persistence.entities.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankAccountApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountResourceTests {

	@LocalServerPort
	private int port;
	

	@Autowired
	private DataFactory dataFactory;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	@DirtiesContext
	public void testRetrieveAccountById() throws JSONException {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/bank-account-api/accounts/10000"),
				HttpMethod.GET, entity, String.class);

		String expected = "{\"id\":10000,\"accountNumber\":\"AAA124574\",\"openedDate\":\"2000-01-31T00:00:00\",\"closedDateTime\":null,\"currentBalance\":100.00,\"allowOverdraft\":false,\"overdraftAmount\":0.00,\"details\":\"Credit Account\"}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	/**
	 * Test for a withdraw transaction
	 * @throws JSONException
	 */
	@Test
	@DirtiesContext
	public void executeAccountWithdrawTransaction_thenAcountBalanceUpdated() throws JSONException {
		 
		 // Scenario: An existing client withdraws from his account
	     // Given an existing client named "pierre-jean" with 100.0 EUR in his account
	     // When he withdraws 10.0 EUR from his account
	     // Then the new balance is 90.0 EUR
		
		// 1. Arrange 
		Transaction transaction = dataFactory.getWithDrawTransaction(); // Transaction withDraw : 10
		// Expected account balance : 100.00 - 10.00 = 90.00 
		String expected = "{\"id\":10000,"
				+ "\"accountNumber\":\"AAA124574\","
				+ "\"openedDate\":\"2000-01-31T00:00:00\","
				+ "\"closedDateTime\":null,"
				+ "\"currentBalance\":90.00,"
				+ "\"allowOverdraft\":false,\"overdraftAmount\":0.00,\"details\":\"Credit Account\"}";
		
		
		// 2. Act 
		
		HttpEntity<Transaction> entity = new HttpEntity<Transaction>(transaction, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/bank-account-api/accounts/10000/transactions/execute"),
				HttpMethod.POST, entity, String.class);
	
		 // 3. Assert		
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
		
	}
	
	/**
	 * Test for a deposit transaction
	 * @throws JSONException
	 */
	@Test
	@DirtiesContext
	public void executeAccountDepositTransaction_thenAcountBalanceUpdated() throws JSONException {
		 // Scenario: An existing client deposit on his account  
	     // Given an existing client named "pierre-jean" with 100.0 EUR in his account  
	     // When he deposit 10.0 EUR from his account  
	     // Then the new balance is 110.0 EUR  
		
		// Deposit transaction
		// 1. Arrange
		Transaction transaction = dataFactory.getDepositTransaction();
		// Expected account current balance : 100.00 + 10.00 = 110.00 
		String expected = "{\"id\":10000,"
				+ "\"accountNumber\":\"AAA124574\","
				+ "\"openedDate\":\"2000-01-31T00:00:00\","
				+ "\"closedDateTime\":null,"
				+ "\"currentBalance\":110.00,"
				+ "\"allowOverdraft\":false,\"overdraftAmount\":0.00,\"details\":\"Credit Account\"}";
		// 2. Act
		HttpEntity<Transaction> entity = new HttpEntity<Transaction>(transaction, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/bank-account-api/accounts/10000/transactions/execute"),
				HttpMethod.POST, entity, String.class);
	
		// 3. Assert
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
