package com.prices.api.service;

import static com.prices.api.model.Brand.LEFTIES;
import static com.prices.api.model.Brand.ZARA;
import static java.math.RoundingMode.FLOOR;
import static org.junit.Assert.assertEquals;

import com.prices.api.exception.rest.NotFoundException;
import com.prices.api.model.PriceDTO;
import java.math.BigDecimal;
import java.util.List;
import java.util.TimeZone;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceServiceTest {

  @Autowired
  @InjectMocks
  private PriceService priceService;

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Before
  public void setUp() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }


  @Test
  public void test1_ok() {
    // Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)

    PriceDTO price = priceService.findActivePrice("2020-06-14T10:00:00.000Z", 35455L, ZARA);

    assertEquals(BigDecimal.valueOf(35.50).setScale(2, FLOOR), price.getAmount());
  }

  @Test
  public void test2_ok() {
    // Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)

    PriceDTO price = priceService.findActivePrice("2020-06-14T16:00:00.000Z", 35455L, ZARA);

    assertEquals(BigDecimal.valueOf(25.45).setScale(2, FLOOR), price.getAmount());
  }

  @Test
  public void test3_ok() {
    // Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)

    PriceDTO price = priceService.findActivePrice("2020-06-14T21:00:00.000Z", 35455L, ZARA);

    assertEquals(BigDecimal.valueOf(35.50).setScale(2, FLOOR), price.getAmount());
  }

  @Test
  public void test4_ok() {
    // Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)

    PriceDTO price = priceService.findActivePrice("2020-06-15T10:00:00.000Z", 35455L, ZARA);

    assertEquals(BigDecimal.valueOf(30.50).setScale(2, FLOOR), price.getAmount());
  }

  @Test
  public void test5_ok() {
    // Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

    PriceDTO price = priceService.findActivePrice("2020-06-16T21:00:00.000Z", 35455L, ZARA);

    assertEquals(BigDecimal.valueOf(38.95).setScale(2, FLOOR), price.getAmount());
  }

  @Test
  public void findActivePrice_withNonExistentProduct_fail() {
    exception.expect(NotFoundException.class);
    exception.expectMessage("No active price found for date [2020-06-16T21:00:00.000Z], product_id [666] and brand [ZARA]");

    priceService.findActivePrice("2020-06-16T21:00:00.000Z", 666L, ZARA);
  }

  @Test
  public void findByBrand_withBrandZara_ok() {
    List<PriceDTO> zaraPrices = priceService.findByBrand(ZARA);

    assertEquals(4, zaraPrices.size());
  }

  @Test
  public void findByBrand_withBrandLefties_ok() {
    List<PriceDTO> leftiesPrices = priceService.findByBrand(LEFTIES);

    assertEquals(2, leftiesPrices.size());
  }
}
