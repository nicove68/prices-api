package com.prices.api.mapper;

import com.prices.api.model.PriceDTO;
import com.prices.api.model.entity.Price;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PriceMapperTest {

  @InjectMocks
  private PriceMapper priceMapper;

  @Spy
  private ModelMapper modelMapper;

  @Test
  public void toPriceDTO_ok() {
    Price entityPrice = Price.builder()
        .id(1234L)
        .brandId(1)
        .startDate(ZonedDateTime.parse("2020-01-01T23:59:59Z"))
        .endDate(ZonedDateTime.parse("2020-01-02T23:59:59Z"))
        .priceList(10)
        .productId(9999L)
        .priority(1)
        .amount(BigDecimal.valueOf(45.55))
        .curr("EUR")
        .build();

    PriceDTO priceDTO = priceMapper.toPriceDTO(entityPrice);

    Assert.assertEquals(Long.valueOf(9999), priceDTO.getProductId());
    Assert.assertEquals(1, priceDTO.getBrandId());
    Assert.assertEquals(10, priceDTO.getPriceList());
    Assert.assertEquals("2020-01-01T23:59:59Z", priceDTO.getStartDate().toString());
    Assert.assertEquals("2020-01-02T23:59:59Z", priceDTO.getEndDate().toString());
    Assert.assertEquals(BigDecimal.valueOf(45.55), priceDTO.getAmount());
    Assert.assertEquals("EUR", priceDTO.getCurr());
  }
}
