package com.bridgeLabz.bookStore.service;

import com.bridgeLabz.bookStore.responsedto.CartResponse;
import com.bridgeLabz.bookStore.exception.BookNotFoundByIdException;
import com.bridgeLabz.bookStore.exception.CartNotFoundByIdException;
import com.bridgeLabz.bookStore.exception.InvalidRequestException;
import com.bridgeLabz.bookStore.exception.UserNotFoundByIdException;
import com.bridgeLabz.bookStore.mapper.CartMapper;
import com.bridgeLabz.bookStore.model.Book;
import com.bridgeLabz.bookStore.model.Cart;
import com.bridgeLabz.bookStore.model.User;
import com.bridgeLabz.bookStore.repository.BookRepository;
import com.bridgeLabz.bookStore.repository.CartRepository;
import com.bridgeLabz.bookStore.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

    private CartRepository cartRepository;
    private CartMapper cartMapper;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    @Transactional
    public CartResponse addToCart(long userId, Integer bookId, Long quantity) {
        if (quantity <= 0) {
            throw new InvalidRequestException("Quantity must be greater than zero");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("User not found with ID: " + userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundByIdException("Book not found with ID: " + bookId));

        Cart cart = cartRepository.findByUserAndBook(user, book)
                .map(existingCart -> {
                    existingCart.setQuantity(existingCart.getQuantity() + quantity);
                    existingCart.setTotalPrice(existingCart.getQuantity() * book.getPrice());
                    return existingCart;
                }).orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setBook(book);
                    newCart.setQuantity(quantity);
                    newCart.setTotalPrice(quantity * book.getPrice());
                    return newCart;
                });

        cartRepository.save(cart);
        return cartMapper.mapToCartResponse(cart);

    }

    @Transactional
    public CartResponse removeFromCartByCartId(long cartId) {
        return cartRepository.findById(cartId)
                .map(cart->{
                    cartRepository.delete(cart);
                    return cartMapper.mapToCartResponse(cart);
                }).orElseThrow(()->new CartNotFoundByIdException("Unable to remove from cart"));
    }

    @Transactional
    public List<CartResponse> removeFromCartByUserId(long userId) {
        return cartRepository.findAllByUserId(userId)
                .stream()
                .map(cart -> {
                    cartRepository.delete(cart);
                    return cartMapper.mapToCartResponse(cart);
                }).toList();
    }

    public CartResponse updateQuantity(long cartId, long quantity) {
        if (quantity <= 0) {
            throw new InvalidRequestException("Quantity must be greater than zero");
        }
        return cartRepository.findById(cartId)
                .map(cart -> {
                    cart.setQuantity(quantity);
                    cart.setTotalPrice(cart.getTotalPrice()*quantity);
                    cart = cartRepository.save(cart);
                    return cartMapper.mapToCartResponse(cart);
                }).orElseThrow(()->new CartNotFoundByIdException("Unable to update quantity"));
    }

    public List<CartResponse> getAllCartItemsForUser(long userId) {
        return cartRepository.findAllByUserId(userId)
                .stream().map(
                        cart -> cartMapper.mapToCartResponse(cart)
                ).toList();
    }

    public List<CartResponse> getAllCartItems() {
        return cartRepository.findAll()
                .stream().map(cart -> cartMapper.mapToCartResponse(cart)).toList();
    }

    public List<Cart> findAllByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    public Optional<Cart> findByCartId(long cartId) {
        return cartRepository.findById(cartId);
    }
}

