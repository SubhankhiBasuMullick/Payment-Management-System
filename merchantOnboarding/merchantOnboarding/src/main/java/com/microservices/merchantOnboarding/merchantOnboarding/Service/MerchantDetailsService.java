package com.microservices.merchantOnboarding.merchantOnboarding.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.Merchant;
import com.microservices.merchantOnboarding.merchantOnboarding.Model.MerchantDetails;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaMerchantRepository;

@Service
public class MerchantDetailsService implements UserDetailsService {



  @Autowired
  private JpaMerchantRepository jpaUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Merchant> user = jpaUserRepository.findByUsername(username);

    user.orElseThrow(()->new UsernameNotFoundException(String.
            format("USER_NOT_FOUND '%s'.",username)));//lambda expression


    return user.map(MerchantDetails::new ).get();
  }

}


