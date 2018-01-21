package com.bankaccount.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankaccount.persistence.entities.GenderType;

@Repository
public interface GenderTypeRepository extends JpaRepository<GenderType, Long>{

}
