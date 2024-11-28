package com.example.demo.models;

public record Product (ProductId productId){

    public Product(ProductId productId) {
        this.productId = productId;
    }

    public ProductId getProductId() {
        return productId;
    }
    @Override
    public String toString() {
        return "{" + productId + "}";
    }

}
