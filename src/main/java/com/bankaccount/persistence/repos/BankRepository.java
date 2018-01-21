package com.bankaccount.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankaccount.persistence.entities.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{

}
