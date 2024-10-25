package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LineItemTest {
    @Test
    void addQuantity() {
        // Given
        String productId = "product-1";
        int quantity = 2;
        int delta = 2;
        LineItem lineItem = new LineItem(productId, quantity);

        // When
        lineItem.addQuantity(delta);

        // Then
        assertThat(lineItem.getQuantity()).isEqualTo(quantity + delta);
    }

    @Test
    void setProductAndGetTotalPrice() {
        // Given
        Product product = new Product("product-1", "Product #1", 5_000);
        int quantity = 2;

        LineItem lineItem = new LineItem(product.getId(), quantity);

        // When
        lineItem.setProduct(product);

        // Then
        assertThat(lineItem.getProductName()).isEqualTo(product.getName());
        assertThat(lineItem.getUnitPrice()).isEqualTo(product.getPrice());
        assertThat(lineItem.getTotalPrice())
                .isEqualTo(product.getPrice() * quantity);
    }

}
