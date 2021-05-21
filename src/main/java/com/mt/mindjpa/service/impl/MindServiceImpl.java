package com.mt.mindjpa.service.impl;

import java.io.FileNotFoundException;
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

import com.mt.mindjpa.exceptionhandling.MIndNotFoundException;
import com.mt.mindjpa.model.MTMind;
import com.mt.mindjpa.repository.MindRepository;
import com.mt.mindjpa.service.MindService;
import com.sun.tools.sjavac.Log;

@Service
public class MindServiceImpl implements MindService{

	@Autowired
	MindRepository mindRepo;
	
	public MTMind addMind(MTMind mind)
	{
		return mindRepo.save(mind);
	}
	
	public List<MTMind> addMinds(List<MTMind> minds)
	{
		List<MTMind> minds1=(List<MTMind>)mindRepo.saveAll(minds);
		return minds1;
	}
	
	public Optional<MTMind> getMindById(int id) throws MIndNotFoundException
	{
		Optional<MTMind> mind=mindRepo.findById(id);
		if(mind.isEmpty())
			throw new MIndNotFoundException();
		else
			return mind;
	}
	
	public List<MTMind> getAllMinds()
	{
		List<MTMind> mindList=(List<MTMind>)mindRepo.findAll();
		return mindList;
	}
	
	public MTMind updateMind(MTMind mind)
	{
		return mindRepo.save(mind);
	}
	
	public void deleteMindById(int id) throws MIndNotFoundException
	{
		if(getMindById(id).isPresent())
		{
			mindRepo.deleteById(id);
		}
		else
		{
			throw new MIndNotFoundException(); 
		}
	}
	
	public void deleteAllMinds() throws MIndNotFoundException
	{
		if(!getAllMinds().isEmpty())
		{
			mindRepo.deleteAll();
		}
		else
		{
			throw new MIndNotFoundException(); 
		}
	}
	
	public void generateExcel() throws IOException
	{
		XSSFWorkbook workBook=new XSSFWorkbook();
		XSSFSheet sheet=workBook.createSheet("minds info");
		List<MTMind> minds=getAllMinds();
		
		XSSFRow headerRow=sheet.createRow(0);
		XSSFCell cell=headerRow.createCell(0);
		cell.setCellValue("ID");
		cell=headerRow.createCell(1);
		cell.setCellValue("NAME");
		cell=headerRow.createCell(2);
		cell.setCellValue("SALARY");
		cell=headerRow.createCell(3);
		cell.setCellValue("TRACK NAME");
		int rowCount=1;
		for(MTMind mind:minds)
		{
			headerRow=sheet.createRow(rowCount++);
			int colCount=0;
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(mind.getId());
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(mind.getName());
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(mind.getSalary());
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(mind.getTrack().getName());
		}
		String filePath=".\\datafiles\\minds.xlsx";
		FileOutputStream fos=new FileOutputStream(filePath);
		workBook.write(fos);
		fos.close();
		Log.info("excel data of minds generated");
	}
}
