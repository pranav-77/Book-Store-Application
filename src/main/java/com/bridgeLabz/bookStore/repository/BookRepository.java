package com.bridgeLabz.bookStore.repository;

import com.bridgeLabz.bookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

