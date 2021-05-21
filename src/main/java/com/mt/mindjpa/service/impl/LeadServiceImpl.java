package com.mt.mindjpa.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.mindjpa.exceptionhandling.LeadNotFoundException;
import com.mt.mindjpa.model.Lead;

import com.mt.mindjpa.repository.LeadRepository;
import com.mt.mindjpa.service.LeadService;
import com.sun.tools.sjavac.Log;

@Service
public class LeadServiceImpl implements LeadService{

	@Autowired
	LeadRepository leadRepo;
	
	public Lead addLead(Lead lead)
	{
		return leadRepo.save(lead);
	}
	
	public List<Lead> addLeads(List<Lead> leads)
	{
		List<Lead> leads1=(List<Lead>)leadRepo.saveAll(leads);
		return leads1;
	}
	
	public Optional<Lead> getLeadById(int id) throws LeadNotFoundException
	{
		Optional<Lead> lead=leadRepo.findById(id);
		if(lead.isEmpty())
			throw new LeadNotFoundException();
		else
			return lead;
	}
	
	public List<Lead> getAllLeads()
	{
		List<Lead> leadList=(List<Lead>)leadRepo.findAll();
		return leadList;
	}
	
	public Lead updateLead(Lead lead)
	{
		Lead result=leadRepo.findById(lead.getId()).get();
		lead.setMinds(result.getMinds());
		return leadRepo.save(lead);
	}
	
	public void deleteLeadById(int id) throws LeadNotFoundException
	{
		if(getLeadById(id).isPresent())
		{
			leadRepo.deleteById(id);
		}
		else
		{
			throw new LeadNotFoundException(); 
		}
	}
	
	public void deleteAllLeads() throws LeadNotFoundException
	{
		if(!getAllLeads().isEmpty())
		{
			leadRepo.deleteAll();
		}
		else
		{
			throw new LeadNotFoundException(); 
		}
	}
	public void generateExcel() throws IOException
	{
		XSSFWorkbook workBook=new XSSFWorkbook();
		XSSFSheet sheet=workBook.createSheet("leads info");
		List<Lead> leads=getAllLeads();
		XSSFRow headerRow=sheet.createRow(0);
		XSSFCell cell=headerRow.createCell(0);
		cell.setCellValue("ID");
		cell=headerRow.createCell(1);
		cell.setCellValue("NAME");
		cell=headerRow.createCell(2);
		cell.setCellValue("SALARY");
		cell=headerRow.createCell(3);
		cell.setCellValue("TRACKS");
		int rowCount=1;
		for(Lead lead:leads)
		{
			headerRow=sheet.createRow(rowCount++);
			int colCount=0;
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(lead.getId());
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(lead.getName());
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(lead.getSalary());
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(lead.getMinds().toString());
			
		}
		String filePath=".\\datafiles\\leads.xlsx";
		FileOutputStream fos=new FileOutputStream(filePath);
		workBook.write(fos);
		fos.close();
		Log.info("excel data of minds generated");
	}
	
}
