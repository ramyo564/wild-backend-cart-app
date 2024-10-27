package com.example.demo.models;

public class LineItem {
    private LineItemId id;
    private ProductId productId;
    private Money unitPrice;
    private int quantity;
    private Money totalPrice;

    public void addQuantity(int quantity){
        this.quantity += quantity;
        this.totalPrice = unitPrice.multiply(quantity);
    }
}
