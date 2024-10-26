package com.example.demo.infrastructure;

import com.example.demo.models.Cart;
import com.example.demo.models.LineItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepository {

    private final LineItemRepository lineItemRepository;

    public CartRepository(LineItemRepository lineItemRepository) {
        this.lineItemRepository = lineItemRepository;
    }

    public Cart find() {
        List<LineItem> lineItems = lineItemRepository.findAll();
        return new Cart(lineItems);
    }

    public void save(Cart cart) {
        cart.getLineItems().forEach(lineItem -> {
            if (lineItem.getId() == null) {
                lineItemRepository.add(lineItem);
                return;
            }
            lineItemRepository.update(lineItem);
        });
    }
}
