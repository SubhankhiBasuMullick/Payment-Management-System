package com.microservices.merchantOnboarding.merchantOnboarding.Controller;

import java.net.URI;
import java.util.Objects;

import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservices.merchantOnboarding.merchantOnboarding.Component.JwtTokenUtil;
import com.microservices.merchantOnboarding.merchantOnboarding.Component.MyAuthenticationException;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.Merchant;
import com.microservices.merchantOnboarding.merchantOnboarding.Model.LoginRequest;
import com.microservices.merchantOnboarding.merchantOnboarding.Model.LoginResponse;
import com.microservices.merchantOnboarding.merchantOnboarding.Model.MerchantDetails;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaMerchantRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JpaAuthenticationController {

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JpaMerchantRepository jpaUserRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("${jwt.signup.uri}")
    public ResponseEntity<?> signup(@RequestBody Merchant merchant)
            throws MyAuthenticationException {

        Merchant duplicateMerchant = jpaUserRepository.findByUsername(merchant.getUsername()).orElse(null);
        try {
            if (duplicateMerchant == null) {
                String encodedPassword = bCryptPasswordEncoderBean().encode(merchant.getPassword());
                merchant.setPassword(encodedPassword);
             //   merchant.setStatus(true);
                Merchant createdUser = jpaUserRepository.save(merchant);
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdUser.getMerchantId()).toUri();// here we are taking the current
                return ResponseEntity.created(uri).build();

            } else {
                throw new NonUniqueResultException();
            }
        } catch (NonUniqueResultException e) {
            throw new MyAuthenticationException("Username already taken! ", e);
        }


        //we need to return the location of the added user
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(createdUser.getId()).toUri();// here we are taking the current
        //request path and adding the id to it

    }

    @RequestMapping(value = "${jwt.login.uri}", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequestDto)
            throws MyAuthenticationException {

        authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        //authentication aka puch tacha complete preparing JWT for the response, below
        final MerchantDetails  userDetails =(MerchantDetails) userDetailsService.loadUserByUsername(loginRequestDto.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
       // String username = jwtTokenUtil.getUsernameFromToken(token);
       // MyUserDetails user = (MyUserDetails) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new LoginResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({MyAuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(MyAuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new MyAuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new MyAuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}

