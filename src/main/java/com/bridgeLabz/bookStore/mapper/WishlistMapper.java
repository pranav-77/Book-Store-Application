package com.bridgeLabz.bookStore.mapper;

import com.bridgeLabz.bookStore.model.Wishlist;
import com.bridgeLabz.bookStore.responsedto.WishlistResponse;
import org.springframework.stereotype.Component;

@Component
public class WishlistMapper {

    public WishlistResponse mapToWishlistResponse(Wishlist wishlist) {
        WishlistResponse wishlistResponse = new WishlistResponse();
        wishlistResponse.setId(wishlist.getId());
        wishlistResponse.setBookId(wishlist.getBook().getBookId());
        wishlistResponse.setUserId(wishlist.getUser().getUserId());
        return wishlistResponse;
    }
}

