package com.example.demo.application;

import com.example.demo.infrastructure.LineItemDAO;
import com.example.demo.infrastructure.ProductDAO;
import com.example.demo.models.Cart;
import com.example.demo.models.LineItem;
import com.example.demo.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CartServiceTest {
    private LineItemDAO lineItemDAO;
    private ProductDAO productDAO;
    private CartService cartService;
    private Product product1;
    private Product product2;
    private List<LineItem> lineItems;

    @BeforeEach
    void setUp() {
        product1 = new Product("product-1", "Product #1", 5_000);
        product2 = new Product("product-2", "Product #2", 3_000);

        lineItems = new ArrayList<>();
        lineItemDAO = mock(LineItemDAO.class);
        productDAO = mock(ProductDAO.class);

        given(lineItemDAO.findALl()).willReturn(lineItems);

        given(productDAO.find("product-1")).willReturn(product1);
        given(productDAO.find("product-2")).willReturn(product2);
        cartService = new CartService(lineItemDAO, productDAO);
    }

    @Test
    @DisplayName("장바구니가 비어있으면 총 가격은 0원")
    void totalPriceIsZero() {
        // Given
        clearCart();

        // When
        Cart cart = cartService.getCart();

        // Then
        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("장바구니에 있는 하나의 상품 가격을 모두 더해서 총 가격을 구한다.")
    void calculateTotalPriceWithOneLineItem() {
        // Given
        int quantity = 2;
        addProductCart(product1, quantity);

        // When
        Cart cart = cartService.getCart();

        // Then
        assertThat(cart.getTotalPrice())
                .isEqualTo(product1.getPrice() * quantity);
    }

    @Test
    @DisplayName("장바구니에 있는 여러 상품의 가격을 모두 더해서 총 가격을 구한다.")
    void calculateTotalPriceWithManyLineItems() {
        // Given
        int quantity1 = 2;
        int quantity2 = 4;
        addProductCart(product1, quantity1);
        addProductCart(product2, quantity2);

        // When
        Cart cart = cartService.getCart();

        // Then
        assertThat(cart.getTotalPrice())
                .isEqualTo(
                        product1.getPrice() * quantity1
                                + product2.getPrice() * quantity2);
    }

    @Test
    @DisplayName("비어있는 장바구니에 상품 담기")
    void addProduct() {
        // Given
        String productId = product1.getId();
        int quantity = 2;

        // When
        cartService.addProduct(productId, quantity);

        // Then
        verify(lineItemDAO).add(argThat(lineItem ->
                lineItem.getProductId().equals(productId)
                        && lineItem.getQuantity() == quantity

        ));
    }

    @Test
    @DisplayName("비어있는 장바구니에 없는 상품 담기")
    void addNewProduct() {
        // Given
        String productId = product1.getId();
        int quantity = 2;

        lineItems.add(new LineItem(product2.getId(), 10));

        // When
        cartService.addProduct(productId, quantity);

        // Then
        verify(lineItemDAO).add(argThat(lineItem ->
                lineItem.getProductId().equals(productId)
                        && lineItem.getQuantity() == quantity

        ));
    }

    @Test
    @DisplayName("장바구니에 이미 있는 상품 추가로 담기")
    void addExtraSameProduct() {
        // Given
        String productId = product1.getId();
        int quantity = 2;
        int oldQuantity = 1;

        lineItems.add(new LineItem(product2.getId(), oldQuantity));

        // When
        cartService.addProduct(productId, quantity);

        // Then
        verify(lineItemDAO).update(argThat(lineItem ->
                lineItem.getProductId().equals(productId)
                        && lineItem.getQuantity() == oldQuantity + quantity

        ));
    }

    private void clearCart() {
        lineItems.clear();
    }

    private void addProductCart(Product product, int quantity) {
        String id = "item-" + (lineItems.size() + 1);
        LineItem lineItem = new LineItem(id, product.getId(), quantity);
        lineItems.add(lineItem);
    }
}
