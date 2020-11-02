package com.prices.api.exception.rest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class InternalServerException extends RestException {

  public InternalServerException() {
    super(RestResponse.builder()
        .status(INTERNAL_SERVER_ERROR)
        .body("Internal Server exception")
        .build());
  }

  public InternalServerException(String body) {
    super(RestResponse.builder()
        .status(INTERNAL_SERVER_ERROR)
        .body(body)
        .build());
  }
}