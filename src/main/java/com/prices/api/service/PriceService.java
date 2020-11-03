package com.prices.api.service;

import static java.lang.String.format;
import static java.time.ZoneOffset.UTC;

import com.prices.api.exception.rest.NotFoundException;
import com.prices.api.mapper.PriceMapper;
import com.prices.api.model.Brand;
import com.prices.api.model.PriceDTO;
import com.prices.api.repository.PriceRepository;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

  private static final Logger logger = LoggerFactory.getLogger(PriceService.class);

  private final PriceRepository priceRepository;
  private final PriceMapper priceMapper;

  public PriceService(PriceRepository priceRepository, PriceMapper priceMapper) {
    this.priceRepository = priceRepository;
    this.priceMapper = priceMapper;
  }

  public List<PriceDTO> findByBrand(Brand brand) {
    logger.info("Find all prices for brand [{}]", brand);

    return priceRepository.findAllByBrandId(brand.getId())
        .stream()
        .map(priceMapper::toPriceDTO)
        .collect(Collectors.toList());
  }

  public PriceDTO findActivePrice(String date, Long productId, Brand brand) {
    logger.info("Find active price for date [{}], product_id [{}] and brand [{}]", date, productId, brand);

    ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
    ZonedDateTime utcZonedDateTime = zonedDateTime.withZoneSameInstant(UTC);
    LocalDateTime utcDateTime = utcZonedDateTime.toLocalDateTime();

    return priceRepository.findActiveProductPrice(utcDateTime, productId, brand.getId())
        .map(priceMapper::toPriceDTO)
        .orElseThrow(() -> new NotFoundException(format(
            "No active price found for date [%s], product_id [%s] and brand [%s]", date, productId, brand)));
  }
}
