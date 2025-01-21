package com.bridgeLabz.bookStore.mapper;

import com.bridgeLabz.bookStore.responsedto.CartResponse;
import com.bridgeLabz.bookStore.model.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartMapper {

    public CartResponse mapToCartResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartId(cart.getCartId());
        cartResponse.setBookId(cart.getBook().getBookId());
        cartResponse.setUserId(cart.getUser().getUserId());
        cartResponse.setQuantity(cart.getQuantity());
        cartResponse.setTotalPrice(cart.getTotalPrice());
        return cartResponse;
    }
}

