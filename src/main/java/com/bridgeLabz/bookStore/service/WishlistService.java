package com.bridgeLabz.bookStore.service;

import com.bridgeLabz.bookStore.exception.BookNotFoundByIdException;
import com.bridgeLabz.bookStore.exception.UserNotFoundByIdException;
import com.bridgeLabz.bookStore.exception.WishlistNotFoundByIdException;
import com.bridgeLabz.bookStore.mapper.WishlistMapper;
import com.bridgeLabz.bookStore.model.Book;
import com.bridgeLabz.bookStore.model.User;
import com.bridgeLabz.bookStore.model.Wishlist;
import com.bridgeLabz.bookStore.repository.BookRepository;
import com.bridgeLabz.bookStore.repository.UserRepository;
import com.bridgeLabz.bookStore.repository.WishlistRepository;
import com.bridgeLabz.bookStore.responsedto.WishlistResponse;
import com.bridgeLabz.bookStore.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepo;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepo;
    private final BookRepository bookRepo;
    private final WishlistMapper wishlistMapper;

    public WishlistResponse addToWishlist(String token, Integer bookId) {
        long userId = jwtUtils.extractUserIdFromToken(token);
        User user = userRepo.findById(userId)
                .orElseThrow(()->new UserNotFoundByIdException("Failed to find user by Id"));
        Book book = bookRepo.findById(bookId).orElseThrow(()->new BookNotFoundByIdException("Failed to find book"));
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setBook(book);
        wishlist.setAddedDate(LocalDate.now());
        wishlistRepo.save(wishlist);
        return wishlistMapper.mapToWishlistResponse(wishlist);
    }

    public List<WishlistResponse> getWishlist(String token) {
        long userId = jwtUtils.extractUserIdFromToken(token);
        return wishlistRepo.findAllByUserId(userId).stream()
                .map(wishlistMapper::mapToWishlistResponse).toList();
    }


    public WishlistResponse removeFromWishlist(Long wishlistId) {
        return wishlistRepo.findById(wishlistId)
                .map(wishlist -> {
                    wishlistRepo.delete(wishlist);
                    return wishlistMapper.mapToWishlistResponse(wishlist);
                }).orElseThrow(()->new WishlistNotFoundByIdException("Failed to find wishlist"));
    }
}

