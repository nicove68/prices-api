package com.prices.api.model;


import com.prices.api.exception.rest.BadRequestException;

public enum Brand {
  ZARA(1),
  LEFTIES(2),
  UTERQUE(3),
  DUTTI(4);

  private final int id;

  Brand(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public static Brand from(String value) {
    for (Brand brand : values()) {
      if (brand.name().equalsIgnoreCase(value))
        return brand;
    }

    throw new BadRequestException("No brand found for " + value);
  }
}
