insert into  ref_gender_type (id, type, code, description, last_updated_date ) values (1, 'MALE','M', 'The male gender', sysdate());
insert into  ref_gender_type (id, type, code, description, last_updated_date ) values (2, 'FEMALE','F', 'The female gender', sysdate());

insert into  ref_transaction_type (id, type, code, description, last_updated_date ) values (1, 'DEPOSIT','D', 'The deposit transaction', sysdate());
insert into  ref_transaction_type (id, type, code, description, last_updated_date ) values (2, 'WITHDRAW','W', 'The withdraw transaction', sysdate());


insert into bank (ID, CODE, NAME, DETAILS)  values (1, '3001', 'BNP Paris Bas', 'Bank BNP Paris Bas');

insert into client (ID, FIRST_NAME, LAST_NAME, GENDER_ID, BIRTH_DATE, CLIENT_NUMBER, PHONE_NUMBER, EMAIL, DETAILS, BANK_ID, LAST_UPDATED_DATE, CREATED_DATE ) 
		values (10000, 'Pierre','Jeans', 1, '1980-10-16', '33348759687','0646854798', 'pierre.jeans@mycompany.com', 'no details',1, sysdate(), sysdate());

insert into address (ID,STREET, POSTAL_CODE, CITY, COUNTRY,	CLIENT_ID, BANK_ID, DETAILS, CREATED_DATE, LAST_UPDATED_DATE)
		values (20000, '16 rue Saint Jacques', '75005', 'Paris', 'France', 10000,null,'Home Address', sysdate(), sysdate());

insert into account(ID,	ACCOUNT_NUMBER, OPENED_DATE, CLIENT_ID, CURRENT_BALANCE, ALLOW_OVERDRAFT, OVERDRAFT_AMOUNT, DETAILS, CLOSED_DATE_TIME, CREATED_DATE, LAST_UPDATED_DATE )
		values (10000, 'AAA124574', '2000-01-31', 10000, 100.0, false, 0.0, 'Credit Account', null, '2000-01-31 14:15:26.289', sysdate() );
insert into transaction(ID, ACCOUNT_ID,	AMOUNT,	BALANCE, DETAILS, TRANSACTION_DATE, TRANSACTION_TYPE_ID)
		values (50000, 10000, '10.00', '100.00', 'Overdraft 10 euros',  sysdate(), 2);