package com.example.demo.application;

import com.example.demo.infrastructure.CartRepository;
import com.example.demo.infrastructure.LineItemRepository;
import com.example.demo.infrastructure.ProductRepository;
import com.example.demo.models.Cart;
import com.example.demo.models.LineItem;
import com.example.demo.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CartServiceTest {

    private Product product;

    private Cart cart;

    private ProductRepository productRepository;
    private CartRepository cartRepository;
    
    private CartService cartService;

    

    @BeforeEach
    void setUp() {
        product = new Product("product-1", "Product #1", 5_000);

        cart = new Cart(List.of());

        cartRepository = mock(CartRepository.class);

        given(cartRepository.find()).willReturn(cart);

        productRepository = mock(ProductRepository.class);

        given(productRepository.find(product.getId())).willReturn(product);

        cartService = new CartService(cartRepository, productRepository);
    }

    @Test
    @DisplayName("장바구니가 비어있으면 총 가격은 0원")
    void totalPriceIsZero() {
        // When
        Cart cart = cartService.getCart();

        // Then
        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }


    @Test
    @DisplayName("비어있는 장바구니에 상품 담기")
    void addProduct() {
        // Given
        String productId = product.getId();
        int quantity = 2;

        // When
        cartService.addProduct(productId, quantity);

        // Then
        verify(cartRepository).save(any());

    }

}
