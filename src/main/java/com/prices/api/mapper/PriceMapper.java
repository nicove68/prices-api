package com.prices.api.mapper;

import com.prices.api.model.PriceDTO;
import com.prices.api.model.entity.Price;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

  private final ModelMapper modelMapper;

  public PriceMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public PriceDTO toPriceDTO(Price price) {
    return modelMapper.map(price, PriceDTO.class);
  }
}
