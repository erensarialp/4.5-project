package com.turkcell.product_service.infrastructure.product;

import org.springframework.stereotype.Component;

import com.turkcell.product_service.domain.entities.Product;

import com.turkcell.product_service.domain.valueobjects.*;

@Component
public class ProductEntityMapper {

    public JpaProductEntity toEntity(Product product) {
        JpaProductEntity productEntity = new JpaProductEntity();
        productEntity.setId(product.getId().getValue());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPriceAmount(product.getPrice().getAmount());
        productEntity.setPriceCurrency(product.getPrice().getCurrency().getCode());
        productEntity.setStockQuantity(product.getStock().getQuantity());
        return productEntity;
    }

    public Product toDomain(JpaProductEntity entity) {
        Price price = new Price(
                entity.getPriceAmount(),
                Currency.fromCode(entity.getPriceCurrency()) // "TRY", "USD", "EUR", "GBP"
        );

        Stock stock = new Stock(entity.getStockQuantity());

        return Product.reconstruct(
                Product.ProductId.fromString(entity.getId().toString()),
                entity.getName(),
                entity.getDescription(),
                price,
                stock);
    }

}
