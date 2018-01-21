package com.bankaccount.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankaccount.persistence.entities.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
