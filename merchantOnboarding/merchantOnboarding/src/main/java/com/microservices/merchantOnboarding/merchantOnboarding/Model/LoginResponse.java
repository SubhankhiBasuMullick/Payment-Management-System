package com.microservices.merchantOnboarding.merchantOnboarding.Model;

import java.io.Serializable;

public class LoginResponse implements Serializable {

  private static final long serialVersionUID = 8317676219297719109L;

  private final String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}