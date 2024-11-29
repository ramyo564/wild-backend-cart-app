package com.example.demo.models;

public record Product (ProductId productId){

    @Override
    public String toString() {
        return "{" + productId + "}";
    }

}
