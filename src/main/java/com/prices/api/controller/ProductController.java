package com.prices.api.controller;

import static com.prices.api.validator.DateValidator.validateISODateFormat;

import com.prices.api.model.Brand;
import com.prices.api.model.PriceDTO;
import com.prices.api.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final PriceService priceService;

  public ProductController(PriceService priceService) {
    this.priceService = priceService;
  }


  @GetMapping("/{productId}/price")
  @Operation(summary = "Find active product price")
  public PriceDTO findActiveProductPrice(
      @RequestHeader(value = "x-brand", defaultValue = "ZARA") String brand,
      @PathVariable Long productId,
      @RequestParam(value = "utc_date") String utcDate) {

    validateISODateFormat(utcDate);
    return priceService.findActivePrice(utcDate, productId, Brand.from(brand));
  }
}