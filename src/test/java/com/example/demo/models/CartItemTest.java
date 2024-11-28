package com.example.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartItemTest {

    private CartItem cartItem;
    private CartItemId cartItemId;
    private Product product;
    private ProductId productId;
    private ArrayList<Product> products;

    @BeforeEach
    void setUp() {
        productId = new ProductId();
        product = new Product(productId);

        CartItemId cartItemId = new CartItemId();
        cartItem = new CartItem(cartItemId, product, 1);
    }

    @Test
    void addQuantity() {
        cartItem.addQuantity(1);
        assertEquals(2, cartItem.getQuantity());
    }

}
