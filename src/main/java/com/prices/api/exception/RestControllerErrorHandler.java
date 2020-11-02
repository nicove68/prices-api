package com.prices.api.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.prices.api.exception.rest.RestException;
import com.prices.api.exception.rest.RestResponse;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerErrorHandler {

  private static final Logger logger = LoggerFactory.getLogger(RestControllerErrorHandler.class);
  private static final String ERROR_EXECUTING = "Error executing {}";


  @ExceptionHandler(Exception.class)
  public ResponseEntity<RestResponse> handleException(HttpServletRequest req, Exception ex) {
    logger.error(ERROR_EXECUTING, req.getRequestURI(), ex);

    RestResponse error = RestResponse.builder()
        .status(INTERNAL_SERVER_ERROR)
        .body(ex.getMessage())
        .build();

    return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(RestException.class)
  public ResponseEntity<RestResponse> handleRestException(HttpServletRequest req, RestException ex) {
    logger.error(ERROR_EXECUTING, req.getRequestURI(), ex);

    RestResponse response = ex.getResponse();
    HttpStatus httpStatus = response.getStatus();

    return new ResponseEntity<>(ex.getResponse(), httpStatus);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<RestResponse> handleInvalidFormatException(HttpServletRequest req, HttpMessageNotReadableException ex) {
    logger.error(ERROR_EXECUTING, req.getRequestURI(), ex);

    RestResponse error = RestResponse.builder()
        .status(BAD_REQUEST)
        .body(ex.getMessage())
        .build();

    return new ResponseEntity<>(error, BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<RestResponse> handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException ex) {
    logger.error(ERROR_EXECUTING, req.getRequestURI(), ex);

    List<String> errors = new ArrayList<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }

    RestResponse error = RestResponse.builder()
        .status(BAD_REQUEST)
        .body("Validation failed with errors")
        .errors(errors)
        .build();

    return new ResponseEntity<>(error, BAD_REQUEST);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  protected ResponseEntity<RestResponse> handleMissingServletRequestParameter(HttpServletRequest req, MissingServletRequestParameterException ex) {
    logger.error(ERROR_EXECUTING, req.getRequestURI(), ex);

    String paramName = ex.getParameterName();
    RestResponse error = RestResponse.builder()
        .status(BAD_REQUEST)
        .body("'" + paramName + "' param is required")
        .build();

    return new ResponseEntity<>(error, BAD_REQUEST);
  }
}
