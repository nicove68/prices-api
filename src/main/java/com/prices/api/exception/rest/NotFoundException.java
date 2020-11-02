package com.prices.api.exception.rest;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends RestException {

  public NotFoundException() {
    super(RestResponse.builder()
        .status(NOT_FOUND)
        .body("The resource you are trying to access does not exist.")
        .build());
  }

  public NotFoundException(String body) {
    super(RestResponse.builder()
        .status(NOT_FOUND)
        .body(body)
        .build());
  }
}
