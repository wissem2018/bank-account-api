## bank-account-api
<b> This API is for a bank account </b>

<b> Technologies </b>
- Spring boot : Spring Data, Spring Rest, Spring hal  
- Database : H2, MySql
- lombok
- Documentation : Swagger2  

## Starting the application    
   mvn spring-boot:run
  
   
## API Documentation

Swagger URL : http://localhost:8080/bank-account-api/swagger-ui.html#/

1) Account Resource :

GET /accounts	: retrieve all accounts    
GET /accounts/{id}	: retrieve an account with id   
GET /accounts/{id}/transactions	: retrive all account transactions   
POST /accounts/{id}/transactions/process POST : process a transaction of a Withdraw (W) or Deposit (D)  

3) Transaction Resource  
GET /transactions  
GET /transactions/{id}    	

## API Tests
1) Unit TEST  

a) Persistence Tests  
  src/test/java/persistence  
  b) Service Tests  
  src/test/java/service  

2) Integration Tests  
a) Resource Tests    
   src/test/java/resource  
    * processAccountWithdrawTransaction_thenAcountBalanceUpdated  
       // Scenario: An existing client withdraws from his account  
	     // Given an existing client named "pierre-jean" with 100.0 EUR in his account  
	     // When he withdraws 10.0 EUR from his account  
	     // Then the new balance is 90.0 EUR  
   
    * processAccountDepositTransaction_thenAcountBalanceUpdated  
       // Scenario: An existing client deposit on his account  
	     // Given an existing client named "pierre-jean" with 100.0 EUR in his account  
	     // When he deposit 10.0 EUR from his account  
	     // Then the new balance is 110.0 EUR  
    
