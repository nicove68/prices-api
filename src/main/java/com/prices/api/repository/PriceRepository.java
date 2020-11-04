package com.prices.api.repository;

import com.prices.api.model.entity.Price;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM prices as p " +
          "WHERE ?1 BETWEEN p.start_date AND p.end_date " +
          "AND p.product_id = ?2 " +
          "AND p.brand_id = ?3 " +
          "ORDER BY p.priority DESC " +
          "LIMIT 1;")
  Optional<Price> findActiveProductPrice(ZonedDateTime date, Long productId, int brandId);
}
