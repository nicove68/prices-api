package com.prices.api.validator;

import static java.time.format.DateTimeFormatter.ofPattern;

import com.prices.api.exception.rest.BadRequestException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateValidator {

  private static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

  private DateValidator() {
  }

  public static void validateISODate(String dateToValidate) {
    try {
      DateTimeFormatter formatter = ofPattern(ISO_8601_DATE_FORMAT);
      formatter.parse(dateToValidate);
    } catch (DateTimeParseException dtpEx) {
      throw new BadRequestException("Invalid date format, please use ISO 8601. "
          + "Examples: 2000-10-31T01:30:00.000Z, 2000-10-31T01:30:00.000-05:00");
    }
  }
}
