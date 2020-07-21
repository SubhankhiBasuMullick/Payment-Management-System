package com.microservices.merchantOnboarding.merchantOnboarding.Repository;

import java.util.List;


import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaTransactionRepository extends JpaRepository<Transaction, Long>{
	
	List<Transaction> findByUsername(String username);
	

}