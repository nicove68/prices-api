package com.prices.api.service;

import com.prices.api.mapper.PriceMapper;
import com.prices.api.model.Brand;
import com.prices.api.model.PriceDTO;
import com.prices.api.repository.PriceRepository;
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
    logger.info("Find all prices for brand {}", brand);

    return priceRepository.findAllByBrandId(brand.getId())
        .stream()
        .map(priceMapper::toPriceDTO)
        .collect(Collectors.toList());
  }
}
