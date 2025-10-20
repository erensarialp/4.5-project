package com.turkcell.product_service.infrastructure.product;

import java.math.BigDecimal;
import java.util.UUID;

import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.valueobjects.Currency;
import com.turkcell.product_service.domain.valueobjects.Price;
import com.turkcell.product_service.domain.valueobjects.Stock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class JpaProductEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "price_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal priceAmount;

    @Column(name = "price_currency", nullable = false, length = 3)
    private String priceCurrency;

    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;

    protected JpaProductEntity() {
        
    }

    public JpaProductEntity(UUID id, String name, String description, BigDecimal priceAmount, String priceCurrency, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
        this.stockQuantity = stockQuantity;
    }

    public Product toDomain() {
        Price price = new Price(this.priceAmount, Currency.fromCode(this.priceCurrency));
        Stock stock = new Stock(this.stockQuantity);
        return Product.reconstruct(
                Product.ProductId.fromString(this.id.toString()),
                this.name,
                this.description,
                price,
                stock
        );
    }

    public static JpaProductEntity fromDomain(Product product) {
        UUID uuid = product.getId().getValue();
        BigDecimal amount = product.getPrice().getAmount();
        String currency = product.getPrice().getCurrency().getCode();
        int quantity = product.getStock().getQuantity();

        return new JpaProductEntity(
                uuid,
                product.getName(),
                product.getDescription(),
                amount,
                currency,
                quantity
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}