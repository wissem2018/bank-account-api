package com.bankaccount.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankaccount.persistence.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
