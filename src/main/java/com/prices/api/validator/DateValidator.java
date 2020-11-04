package com.prices.api.validator;

import static java.time.format.DateTimeFormatter.ofPattern;

import com.prices.api.exception.rest.BadRequestException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateValidator {

  private static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

  private DateValidator() {
  }

  public static void validateISODateFormat(String dateToValidate) {
    try {
      DateTimeFormatter formatter = ofPattern(ISO_8601_DATE_FORMAT);
      formatter.parse(dateToValidate);
    } catch (DateTimeParseException dtpEx) {
      throw new BadRequestException("Invalid date, please use ISO 8601 format in UTC timezone. "
          + "e.g.: 2000-10-31T01:30:00.000Z");
    }
  }
}
