# bank-account-api
<b> This API is for a bank account </b>

<b> Technologies </b>
- Spring boot : Spring Data, Spring Rest, Spring hal  
- Database : H2, MySql  
- Documentation : Swagger2  

<b>Starting the application <b>
   mvn spring-boot:run
  
   
<b>Resource API </b>

Swagger URL : http://localhost:8080/bank-account-api/swagger-ui.html#/

1) Account Resource :

GET /accounts	: retrieve all accounts    
GET /accounts/{id}	: retrieve an account with id   
GET /accounts/{id}/transactions	: retrive all account transactions   
POST /accounts/{id}/transactions/execute	POST : execute a transaction of a Withdraw (W) or Deposit (D)  

2) Client Resource     
GET /clients  
GET /clients/{id}      	

3) Transaction Resource  
GET /transactions  
GET /transactions/{id}    	

1) Unit TEST  
a) Persistence Tests  
  src/test/java/persistence  
  b) Service Tests  
  src/test/java/service  
2) Integration Tests  
a) Resource Tests    
   
   src/test/java/resource  
    * executeAccountWithdrawTransaction_thenAcountBalanceUpdated  
       // Scenario: An existing client withdraws from his account  
	     // Given an existing client named "pierre-jean" with 100.0 EUR in his account  
	     // When he withdraws 10.0 EUR from his account  
	     // Then the new balance is 90.0 EUR  
   
    * executeAccountDepositTransaction_thenAcountBalanceUpdated  
       // Scenario: An existing client withdraws from his account  
	     // Given an existing client named "pierre-jean" with 100.0 EUR in his account  
	     // When he withdraws 10.0 EUR from his account  
	     // Then the new balance is 110.0 EUR  
    * executeAccountWithdrawTransaction_thenInsufficientFundsException  
      // Scenario: An existing client withdraws from his account  
	     // Given an existing client named "pierre-jean" with 100.0 EUR in his account  
	     // When he withdraws 150.0 EUR from his account  
	     // Then the transaction is aborted and no changes in his account, balance = 100.00  

