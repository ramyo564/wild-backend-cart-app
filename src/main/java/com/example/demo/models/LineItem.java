package com.example.demo.models;

public class LineItem {
    private String productId;
    private String id;
    private int quantity;

    private String productName;
    private int unitPrice;

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

    public String getProductName() {
        return productName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }


    // domain logic
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int getTotalPrice() {
        return unitPrice * quantity;
    }

    public void setProduct(Product product) {
        this.productName = product.getName();
        this.unitPrice = product.getPrice();
    }
}
