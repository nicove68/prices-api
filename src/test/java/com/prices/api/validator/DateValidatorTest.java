package com.prices.api.validator;

import static com.prices.api.validator.DateValidator.validateISODate;

import com.prices.api.exception.rest.BadRequestException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DateValidatorTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void isoDateUtc_ok() {
    String date = "2000-10-31T01:30:00.000Z";

    validateISODate(date);
  }

  @Test
  public void isoDate_ok() {
    String date = "2000-10-31T01:30:00.000-05:00";

    validateISODate(date);
  }

  @Test
  public void onlyDateTime_fail() {
    exception.expect(BadRequestException.class);
    exception.expectMessage("Invalid date format, please use ISO 8601. Examples: 2000-10-31T01:30:00.000Z, 2000-10-31T01:30:00.000-05:00");

    String date = "2000-10-31T01:30:00";

    validateISODate(date);
  }

  @Test
  public void onlyDate_fail() {
    exception.expect(BadRequestException.class);
    exception.expectMessage("Invalid date format, please use ISO 8601. Examples: 2000-10-31T01:30:00.000Z, 2000-10-31T01:30:00.000-05:00");

    String date = "2000-10-31";

    validateISODate(date);
  }
}
