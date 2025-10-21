package com.turkcell.product_service.infrastructure.product;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.entities.Product.ProductId;
import com.turkcell.product_service.domain.repositories.ProductRepository;

public class ProductRepositoryAdapter implements ProductRepository {

    private final SpringDataProductRepository repository;
    private final ProductEntityMapper productEntityMapper;

    public ProductRepositoryAdapter(SpringDataProductRepository repository, ProductEntityMapper productEntityMapper) {
        this.repository = repository;
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    public Product save(Product product) {
        JpaProductEntity entity = productEntityMapper.toEntity(product);
        entity = repository.save(entity);
        return productEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return repository
                .findById(id.getValue())
                .map(productEntityMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll()
                .stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        return repository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Product> findInStockProducts() {
        return repository.findInStockProducts()
                .stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Product> findOutOfStockProducts() {
        return repository.findOutOfStockProducts()
                .stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        return repository.findByPriceRange(
                BigDecimal.valueOf(minPrice),
                BigDecimal.valueOf(maxPrice))
                .stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(ProductId id) {
        repository.deleteById(id.getValue());
    }

    @Override
    public boolean existsById(ProductId id) {
        return repository.existsById(id.getValue());
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long countInStockProducts() {
        return repository.countInStockProducts();
    }

}
