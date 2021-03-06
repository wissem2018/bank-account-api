package com.bankaccount.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankaccount.persistence.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
