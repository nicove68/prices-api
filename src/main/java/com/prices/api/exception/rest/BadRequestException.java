package com.prices.api.exception.rest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;
import java.util.Map;

public class BadRequestException extends RestException {

  public BadRequestException(String body) {
    super(RestResponse.builder()
        .status(BAD_REQUEST)
        .body(body)
        .build());
  }

  public BadRequestException(String body, Map<String, List<String>> errorAttributes) {
    super(RestResponse.builder()
        .status(BAD_REQUEST)
        .body(body)
        .errorAttributes(errorAttributes)
        .build());
  }
}
