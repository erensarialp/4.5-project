package com.turkcell.product_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    @GetMapping
    public String getProducts() {
        return "Products";
    }

    @GetMapping("{id}")
    public String getProductById(@PathVariable String id) {
        return "Product " + id;
    }

    @PostMapping
    public String createProduct() {
        return "Product created successfully";
    }

    @PutMapping("{id}")
    public String updateProduct(@PathVariable String id) {
        return "Product updated successfully";
    }

    @DeleteMapping("{id}")
    public String deleteProduct(@PathVariable String id) {
        return "Product deleted successfully";
    }
}