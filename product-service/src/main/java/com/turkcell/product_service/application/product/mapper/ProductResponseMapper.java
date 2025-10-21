package com.turkcell.product_service.application.product.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.product_service.application.product.dto.ProductResponse;
import com.turkcell.product_service.domain.entities.Product;

@Component
public class ProductResponseMapper {

    public ProductResponse toResponse(Product product)
    {
        return new ProductResponse();
    }

}
