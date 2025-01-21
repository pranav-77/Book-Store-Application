package com.bridgeLabz.bookStore.mapper;

import com.bridgeLabz.bookStore.model.Feedback;
import com.bridgeLabz.bookStore.requestdto.FeedbackRequest;
import com.bridgeLabz.bookStore.responsedto.FeedbackResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FeedbackMapper {

    public Feedback mapToFeedback(FeedbackRequest feedbackRequest,Feedback feedback) {
        feedback.setRating(feedbackRequest.getRating());
        feedback.setComment(feedbackRequest.getComment());
        feedback.setSubmittedDate(LocalDate.now());
        return feedback;
    }

    public FeedbackResponse mapToFeedbackResponse(Feedback feedback) {
        FeedbackResponse feedbackResponse = new FeedbackResponse();
        feedbackResponse.setFeedbackId(feedback.getFeedbackId());
        feedbackResponse.setBookId(feedback.getBook().getBookId());
        feedbackResponse.setUserId(feedback.getUser().getUserId());
        feedbackResponse.setComment(feedback.getComment());
        feedbackResponse.setRating(feedback.getRating());
        feedbackResponse.setSubmittedDate(feedback.getSubmittedDate());
        return feedbackResponse;
    }
}

