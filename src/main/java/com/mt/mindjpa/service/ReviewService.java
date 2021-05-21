package com.mt.mindjpa.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mt.mindjpa.exceptionhandling.ReviewNotDoneException;
import com.mt.mindjpa.model.Review;

@Service
public interface ReviewService {

	public Review addReview(Review review);
	public List<Review> addReviews(List<Review> reviews);
	public Optional<Review> getReviewById(int id) throws ReviewNotDoneException;
	public List<Review> getAllReviews();
	public Review updateReview(Review review);
	public void deleteReviewById(int id) throws ReviewNotDoneException;
	public void deleteAllReviews() throws ReviewNotDoneException;
	public void generateExcel() throws IOException;
}
