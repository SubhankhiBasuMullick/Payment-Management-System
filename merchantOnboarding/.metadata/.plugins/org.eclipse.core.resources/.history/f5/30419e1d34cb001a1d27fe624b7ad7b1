package com.microservices.merchantOnboarding.merchantOnboarding.Repository;


import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.Merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaMerchantRepository extends JpaRepository<Merchant,Integer> {

    Optional<Merchant> findByUsername(String username);
}
