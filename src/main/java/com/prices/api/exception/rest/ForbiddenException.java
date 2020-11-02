package com.prices.api.exception.rest;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class ForbiddenException extends RestException {

  public ForbiddenException() {
    super(RestResponse.builder()
        .status(FORBIDDEN)
        .body("You don't have access to this resource.")
        .build());
  }

  public ForbiddenException(String body) {
    super(RestResponse.builder()
        .status(FORBIDDEN)
        .body(body)
        .build());
  }
}
