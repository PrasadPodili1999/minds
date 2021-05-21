package com.mt.mindjpa.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.mindjpa.exceptionhandling.ReviewNotDoneException;
import com.mt.mindjpa.model.MTMind;
import com.mt.mindjpa.model.Review;
import com.mt.mindjpa.repository.ReviewRepository;
import com.mt.mindjpa.service.ReviewService;
import com.sun.tools.sjavac.Log;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewRepository reviewRepo;
	
	public Review addReview(Review review)
	{
		return reviewRepo.save(review);
	}
	
	public List<Review> addReviews(List<Review> reviews)
	{
		List<Review> reviews1=(List<Review>)reviewRepo.saveAll(reviews);
		return reviews1;
	}
	
	public Optional<Review> getReviewById(int id) throws ReviewNotDoneException
	{
		Optional<Review> review=reviewRepo.findById(id);
		if(review.isEmpty())
			throw new ReviewNotDoneException();
		else
			return review;
	}
	
	public List<Review> getAllReviews()
	{
		List<Review> reviewList=(List<Review>)reviewRepo.findAll();
		return reviewList;
	}
	
	public Review updateReview(Review review)
	{
		return reviewRepo.save(review);
	}
	
	public void deleteReviewById(int id) throws ReviewNotDoneException
	{
		if(getReviewById(id).isPresent())
		{
			reviewRepo.deleteById(id);
		}
		else
		{
			throw new ReviewNotDoneException(); 
		}
	}
	
	public void deleteAllReviews() throws ReviewNotDoneException
	{
		if(!getAllReviews().isEmpty())
		{
			reviewRepo.deleteAll();
		}
		else
		{
			throw new ReviewNotDoneException(); 
		}
	}
	
	public void generateExcel() throws IOException
	{
		XSSFWorkbook workBook=new XSSFWorkbook();
		XSSFSheet sheet=workBook.createSheet("minds info");
		List<Review> reviews=getAllReviews();
		
		XSSFRow headerRow=sheet.createRow(0);
		XSSFCell cell=headerRow.createCell(0);
		cell.setCellValue("ID");
		cell=headerRow.createCell(1);
		cell.setCellValue("CAPABILITY_NO");
		cell=headerRow.createCell(2);
		cell.setCellValue("STATUS");
		cell=headerRow.createCell(3);
		cell.setCellValue("TAKEN_FOR");
		cell=headerRow.createCell(4);
		cell.setCellValue("TAKEN_BY");
		int rowCount=1;
		for(Review review:reviews)
		{
			headerRow=sheet.createRow(rowCount++);
			int colCount=0;
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(review.getId());
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(review.getCapabilityNo());
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(review.getStatus());
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(review.getTakenFor().getName());
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(review.getTakenBy().getName());
		}
		String filePath=".\\datafiles\\reviews.xlsx";
		FileOutputStream fos=new FileOutputStream(filePath);
		workBook.write(fos);
		fos.close();
		Log.info("excel data of minds generated");
	}
}
