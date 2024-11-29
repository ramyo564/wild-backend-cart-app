package com.example.demo.models;

public class CartItem {

    private final CartItemId id;
    private final Product product;
    private int quantity;

    public CartItem(CartItemId id, Product product, int quantity) {
        if (id == null) {
            throw new IllegalArgumentException("id는 null일 수 없습니다");
        }
        if (product == null) {
            throw new IllegalArgumentException("product는 null일 수 없습니다");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("수량은 1 이상이어야 합니다");
        }
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("추가할 수량은 0 이상이어야 합니다");
        }
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
