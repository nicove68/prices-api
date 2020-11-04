package com.prices.api.mapper;

import com.prices.api.model.PriceDTO;
import com.prices.api.model.entity.Price;
import java.time.ZoneId;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {


  public PriceDTO toPriceDTO(Price price, ZoneId zoneId) {
    return PriceDTO.builder()
        .productId(price.getProductId())
        .brandId(price.getBrandId())
        .priceList(price.getPriceList())
        .startDate(price.getStartDate().withZoneSameInstant(zoneId))
        .endDate(price.getEndDate().withZoneSameInstant(zoneId))
        .amount(price.getAmount())
        .curr(price.getCurr())
        .build();
  }
}
