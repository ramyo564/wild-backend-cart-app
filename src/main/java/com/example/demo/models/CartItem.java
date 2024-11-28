package com.example.demo.models;

public class CartItem {

    private final CartItemId id;
    private final Product product;
    private int quantity;

    public CartItem(CartItemId id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "CartItem{"
                + "id="
                + id
                + ", product="
                + product
                + ", quantity="
                + quantity + "}";
    }
}
