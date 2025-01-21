package com.bridgeLabz.bookStore.repository;

import com.bridgeLabz.bookStore.model.Book;
import com.bridgeLabz.bookStore.model.Cart;
import com.bridgeLabz.bookStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserAndBook(User user, Book book);
    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    List<Cart> findAllByUserId(@Param("userId") Long userId);
}