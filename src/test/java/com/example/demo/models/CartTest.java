package com.example.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.exceptions.ExceedTotalQuantity;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CartTest {

    private ArrayList<CartItem> cartItems;
    private Product product;
    private ProductId productId;
    private CartItem cartItem;
    private CartItemId cartItemId;
    private Cart cart;

    @BeforeEach
    void setUp() {
        productId = new ProductId();
        product = new Product(productId);
        cartItems = new ArrayList<>();
        cartItemId = new CartItemId();
        cartItem = new CartItem(cartItemId, product, 1);
        cartItems.add(cartItem);

        cart = new Cart(cartItems, 1, product);
    }

    @DisplayName("기존의 상품을 추가할 경우")
    @Test
    void addCartItem() {
        cart.addCartItem(productId, 1);
        assertEquals(2, cart.getTotalQuantity());
        assertEquals(1, cartItems.size());
    }

    @DisplayName("새로운 상품을 추가할 경우")
    @Test
    void addNewCartItem() {
        ProductId newProductId = new ProductId();
        cart.addCartItem(newProductId, 1);
        assertEquals(2, cart.getTotalQuantity());
        assertEquals(2, cartItems.size());
    }

    @DisplayName("장바구니 총계가 20을 넘길 경우")
    @Test
    void checkTotalQuantityLimit() {
        ExceedTotalQuantity exception =
                assertThrows(ExceedTotalQuantity.class, () -> {
                    cart.addCartItem(productId, 20);
                });

        assertEquals(
                "장바구니 총 합계는 20을 넘길 수 없습니다."
                , exception.getMessage());
    }

    @DisplayName("장바구니 비우기")
    @Test
    void madeCartZero() {
        cart.madeCartZero();
        assertEquals(0, cart.getTotalQuantity());
        assertEquals(0, cart.getCartList().size());
    }

    @DisplayName("장바구니 목록 보기")
    @Test
    void getCartList() {
        assertTrue(cart.getCartList().toString().contains("quantity=1"));
        assertTrue(cart.getCartList().toString()
                .contains("product={" + productId + "}"));
        assertTrue(cart.getCartList().toString()
                .contains("id=" + cartItemId));
    }
}
