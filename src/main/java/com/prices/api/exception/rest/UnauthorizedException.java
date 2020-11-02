package com.prices.api.exception.rest;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class UnauthorizedException extends RestException {

  public UnauthorizedException() {
    super(RestResponse.builder()
        .status(UNAUTHORIZED)
        .body("Authentication required.")
        .build());
  }

  public UnauthorizedException(String body) {
    super(RestResponse.builder()
        .status(UNAUTHORIZED)
        .body(body)
        .build());
  }
}