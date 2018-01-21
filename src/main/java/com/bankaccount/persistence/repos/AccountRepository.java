package com.bankaccount.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankaccount.persistence.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
