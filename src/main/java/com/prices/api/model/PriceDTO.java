package com.prices.api.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceDTO {

  private Long productId;
  private int brandId;
  private int priceList;
  private ZonedDateTime startDate;
  private ZonedDateTime endDate;
  private BigDecimal amount;
  private String curr;
}
