package com.prices.api.controller;

import com.prices.api.model.PriceDTO;
import com.prices.api.service.PriceService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PriceController {

  private final PriceService priceService;

  public PriceController(PriceService priceService) {
    this.priceService = priceService;
  }

  @GetMapping
  public List<PriceDTO> findAllPrices() {
    return priceService.findAll();
  }

}