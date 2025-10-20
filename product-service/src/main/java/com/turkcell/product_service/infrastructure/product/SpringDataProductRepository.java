package com.turkcell.product_service.infrastructure.product;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<JpaProductEntity, UUID>{

}
