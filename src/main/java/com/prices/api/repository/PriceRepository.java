package com.prices.api.repository;

import com.prices.api.model.entity.Price;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {

  List<Price> findAll();
}
