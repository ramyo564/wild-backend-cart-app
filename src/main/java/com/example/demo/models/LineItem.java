package com.example.demo.models;

public class LineItem {
    private String productId;
    private String id;
    private int quantity;

    private String productName;
    private int unitPrice;
    private int totalPrice;

    public LineItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }


    public LineItem(String id, String productId, int quantity) {
        this.productId = productId;
        this.id = id;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

}
