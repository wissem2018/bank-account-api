package com.bankaccount.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankaccount.persistence.entities.TransactionType;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long>{

}
