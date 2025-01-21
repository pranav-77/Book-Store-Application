package com.bridgeLabz.bookStore.service;

import com.bridgeLabz.bookStore.exception.BookNotFoundByIdException;
import com.bridgeLabz.bookStore.exception.UserNotFoundByIdException;
import com.bridgeLabz.bookStore.mapper.FeedbackMapper;
import com.bridgeLabz.bookStore.model.Book;
import com.bridgeLabz.bookStore.model.Feedback;
import com.bridgeLabz.bookStore.model.User;
import com.bridgeLabz.bookStore.repository.BookRepository;
import com.bridgeLabz.bookStore.repository.FeedbackRepository;
import com.bridgeLabz.bookStore.repository.UserRepository;
import com.bridgeLabz.bookStore.requestdto.FeedbackRequest;
import com.bridgeLabz.bookStore.responsedto.FeedbackResponse;
import com.bridgeLabz.bookStore.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService {

    private FeedbackMapper feedbackMapper;
    private FeedbackRepository feedbackRepo;
    private UserRepository userRepo;
    private BookRepository bookRepo;
    private JwtUtils jwtUtils;

    public FeedbackResponse submitFeedback(String token, Integer bookId, FeedbackRequest feedbackRequest) {
        long userId = jwtUtils.extractUserIdFromToken(token);
        User user = userRepo.findById(userId)
                .orElseThrow(()->new UserNotFoundByIdException("Failed to find user"));
        Book book = bookRepo.findById(bookId)
                .orElseThrow(()->new BookNotFoundByIdException("Failed to find book"));
        Feedback feedback = feedbackMapper.mapToFeedback(feedbackRequest,new Feedback());
        feedback.setUser(user);
        feedback.setBook(book);
        feedback = feedbackRepo.save(feedback);
        return feedbackMapper.mapToFeedbackResponse(feedback);
    }


    public List<FeedbackResponse> getFeedbackForBook(Integer bookId) {
        return feedbackRepo.findAllByBookId(bookId)
                .stream()
                .map(feedback ->
                        feedbackMapper.mapToFeedbackResponse(feedback)).toList();
    }
}

