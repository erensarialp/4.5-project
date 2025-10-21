package com.turkcell.product_service.infrastructure.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataProductRepository extends JpaRepository<JpaProductEntity, UUID> {

    List<JpaProductEntity> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM JpaProductEntity p WHERE p.stockQuantity > 0")
    List<JpaProductEntity> findInStockProducts();

    @Query("SELECT p FROM JpaProductEntity p WHERE p.stockQuantity <= 0")
    List<JpaProductEntity> findOutOfStockProducts();

    @Query("SELECT p FROM JpaProductEntity p WHERE p.priceAmount BETWEEN :minPrice AND :maxPrice")
    List<JpaProductEntity> findByPriceRange(@Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);

    @Query("SELECT COUNT(p) FROM JpaProductEntity p WHERE p.stockQuantity > 0")
    long countInStockProducts();
}
