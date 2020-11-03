package com.prices.api.service;

import static com.prices.api.model.Brand.LEFTIES;
import static com.prices.api.model.Brand.ZARA;
import static org.junit.Assert.assertEquals;

import com.prices.api.model.PriceDTO;
import java.util.List;
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
  public ExpectedException exceptionRule = ExpectedException.none();


  @Test
  public void findByBrand_withBrandZara_ok() {
    List<PriceDTO> zaraPrices = priceService.findByBrand(ZARA);

    assertEquals(4, zaraPrices.size());
  }

  @Test
  public void findByBrand_withBrandLefties_ok() {
    List<PriceDTO> zaraPrices = priceService.findByBrand(LEFTIES);

    assertEquals(2, zaraPrices.size());
  }

}
