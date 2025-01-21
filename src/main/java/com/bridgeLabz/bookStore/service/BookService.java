package com.bridgeLabz.bookStore.service;

import com.bridgeLabz.bookStore.requestdto.BookRequest;
import com.bridgeLabz.bookStore.responsedto.BookResponse;
import com.bridgeLabz.bookStore.exception.BookNotFoundByIdException;
import com.bridgeLabz.bookStore.mapper.BookMapper;
import com.bridgeLabz.bookStore.model.Book;
import com.bridgeLabz.bookStore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookService {

    private BookRepository bookRepo;
    private BookMapper bookMapper;

    public BookResponse addBook(BookRequest bookRequest) {

        Book book = bookMapper.mapToBook(bookRequest, new Book());
        book = bookRepo.save(book);
        return bookMapper.mapToBookResponse(book);
    }

    public BookResponse getBookById(int bookId) {
        return bookRepo.findById(bookId)
                .map(book -> {
                    return bookMapper.mapToBookResponse(book);
                }).orElseThrow(()->new BookNotFoundByIdException("Not able to get book"));
    }
    public BookResponse deleteBookById(int bookId) {
        return bookRepo.findById(bookId)
                .map(book->{
                    bookRepo.delete(book);
                    return bookMapper.mapToBookResponse(book);
                }).orElseThrow(()->new BookNotFoundByIdException("Not able to delete book"));
    }

    public List<BookResponse> getAllBooks() {
        return bookRepo.findAll()
                .stream()
                .map(book-> {
                    return bookMapper.mapToBookResponse(book);
                }).toList();
    }

    public BookResponse changeBookPrice(int bookId, double price) {
        return bookRepo.findById(bookId)
                .map(book->{
                    book.setPrice(price);
                    book = bookRepo.save(book);
                    return bookMapper.mapToBookResponse(book);
                }).orElseThrow(() -> new BookNotFoundByIdException("Unable to change price"));
    }

    public BookResponse changeBookQuantity(int bookId, long quantity) {
        return bookRepo.findById(bookId)
                .map(book->{
                    book.setQuantity(quantity);
                    book = bookRepo.save(book);
                    return bookMapper.mapToBookResponse(book);
                }).orElseThrow(() -> new BookNotFoundByIdException("Unable to change quantity"));
    }

    public void updateBook(Book book) {
        bookRepo.save(book);
    }
}
