package com.microservices.merchantOnboarding.merchantOnboarding.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.Merchant;

@Repository
public interface JpaMerchantRepository extends JpaRepository<Merchant,Integer> {

    Optional<Merchant> findByUsername(String username);
}
