package com.prices.api.service;

import static java.lang.String.format;

import com.prices.api.exception.rest.NotFoundException;
import com.prices.api.mapper.PriceMapper;
import com.prices.api.model.Brand;
import com.prices.api.model.PriceDTO;
import com.prices.api.repository.PriceRepository;
import java.time.ZonedDateTime;
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

  /**
   * Find an active product price
   *
   * @param utcDate string date with ISO_8601 format in UTC timezone
   * @param productId the id of the product
   * @param brand the brand of the product
   *
   * @return the active price or throw 404 exception if there is not
   *
   * NOTE: start_date and end_date of prices are stored in database in UTC timezone,
   * for that reason the external consumers of prices-api must use date param in UTC too
   *
   */
  public PriceDTO findActivePrice(String utcDate, Long productId, Brand brand) {
    logger.info("Find active price for date [{}], product_id [{}] and brand [{}]", utcDate, productId, brand);

    ZonedDateTime utcZonedDateTime = ZonedDateTime.parse(utcDate);

    return priceRepository.findActiveProductPrice(utcZonedDateTime, productId, brand.getId())
        .map(priceMapper::toPriceDTO)
        .orElseThrow(() -> new NotFoundException(format(
            "No active price found for date [%s], product_id [%s] and brand [%s]", utcDate, productId, brand)));
  }
}
