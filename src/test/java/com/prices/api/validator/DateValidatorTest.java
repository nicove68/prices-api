package com.prices.api.validator;

import static com.prices.api.validator.DateValidator.validateISODateFormat;

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

    validateISODateFormat(date);
  }

  @Test
  public void isoDate_withoutUtcFormat_fail() {
    exception.expect(BadRequestException.class);
    exception.expectMessage("Invalid date, please use ISO 8601 format in UTC timezone. e.g.: 2000-10-31T01:30:00.000Z");

    String date = "2000-10-31T01:30:00.000-05:00";

    validateISODateFormat(date);
  }

  @Test
  public void onlyDateTime_fail() {
    exception.expect(BadRequestException.class);
    exception.expectMessage("Invalid date, please use ISO 8601 format in UTC timezone. e.g.: 2000-10-31T01:30:00.000Z");

    String date = "2000-10-31T01:30:00";

    validateISODateFormat(date);
  }

  @Test
  public void onlyDate_fail() {
    exception.expect(BadRequestException.class);
    exception.expectMessage("Invalid date, please use ISO 8601 format in UTC timezone. e.g.: 2000-10-31T01:30:00.000Z");

    String date = "2000-10-31";

    validateISODateFormat(date);
  }
}
