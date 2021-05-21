package com.mt.mindjpa.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mt.mindjpa.model.MTMind;
import com.mt.mindjpa.model.Review;
import com.mt.mindjpa.service.MindService;
import com.mt.mindjpa.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@RequestMapping(value = "/addReview", method = RequestMethod.POST)
	public ResponseEntity<Review> addReview(@RequestBody Review review)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(review));
	}
	
	@RequestMapping(value = "/addReviews", method = RequestMethod.POST)
	public  ResponseEntity<List<Review>> addReviews(@RequestBody List<Review> reviews)
	{
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReviews(reviews));
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public  ResponseEntity<Optional<Review>> getReviewById(@PathVariable("id") int id)
	{
		try
		{
			return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewById(id));
		}
		catch(Exception e)
		{
			log.error("review not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<List<Review>> getAllReviews()
	{
		return ResponseEntity.status(HttpStatus.OK).body(reviewService.getAllReviews());
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Review> updateReview(@RequestBody Review review)
	{
		return ResponseEntity.status(HttpStatus.OK).body(reviewService.updateReview(review));
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReviewById(@PathVariable("id") int id)
	{
		try
		{
			reviewService.deleteReviewById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e)
		{
			log.error("review not available");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllReviews()
	{
		try
		{
			reviewService.deleteAllReviews();
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e)
		{
			log.error("reviews not available");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public ResponseEntity<Void> generateExcel()
	{
		try {
			reviewService.generateExcel();
		} catch (IOException e) {
			log.error("failed to download excel sheet");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
