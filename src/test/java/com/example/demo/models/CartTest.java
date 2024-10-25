package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CartTest {
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product("product-1", "Product #1", 5_000);
        product2 = new Product("product-2", "Product #2", 3_000);
    }

    @Test
    @DisplayName("getTotalPrice - 빈 장바구니")
    void getTotalPrice() {
        Cart cart = new Cart(List.of());
        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("장바구니에 있는 하나의 상품 가격을 모두 더해서 총 가격을 구한다.")
    void getTotalPriceWithLineItem() {

        int quantity = 2;

        Cart cart = new Cart(List.of(
                createLineItem(product1, quantity)
        ));
        assertThat(cart.getTotalPrice())
                .isEqualTo(product1.getPrice() * quantity);
    }

    @Test
    @DisplayName("장바구니에 있는 여러 상품 가격을 모두 더해서 총 가격을 구한다.")
    void getTotalPriceWithManyLineItem() {

        int quantity1 = 2;
        int quantity2 = 3;

        Cart cart = new Cart(List.of(
                createLineItem(product1, quantity1),
                createLineItem(product2, quantity2)
        ));

        assertThat(cart.getTotalPrice())
                .isEqualTo(
                        product1.getPrice() * quantity1
                                + product2.getPrice() * quantity2
                );
    }

    private LineItem createLineItem(Product product, int quantity) {
        LineItem lineItem = new LineItem(product.getId(), quantity);
        lineItem.setProduct(product);
        return lineItem;
    }
}
