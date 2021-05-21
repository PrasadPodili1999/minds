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

import com.mt.mindjpa.exceptionhandling.TrackNotFoundException;
import com.mt.mindjpa.model.Track;
import com.mt.mindjpa.repository.TrackRepository;
import com.mt.mindjpa.service.TrackService;
import com.sun.tools.sjavac.Log;

@Service
public class TrackServiceImpl implements TrackService{

	@Autowired
	TrackRepository trackRepo;
	
	public Track addTrack(Track track)
	{
		return trackRepo.save(track);
	}
	
	public List<Track> addTracks(List<Track> tracks)
	{
		List<Track> tracks1=(List<Track>)trackRepo.saveAll(tracks);
		return tracks1;
	}
	
	public Optional<Track> getTrackById(int id) throws TrackNotFoundException
	{
		Optional<Track> track=trackRepo.findById(id);
		if(track.isEmpty())
			throw new TrackNotFoundException();
		else
			return track;
	}
	
	public List<Track> getAllTracks()
	{
		List<Track> trackList=(List<Track>)trackRepo.findAll();
		return trackList;
	}
	
	public Track updateTrack(Track track)
	{
		return trackRepo.save(track);
	}
	
	public void deleteTrackById(int id) throws TrackNotFoundException
	{
		if(getTrackById(id).isPresent())
		{
			trackRepo.deleteById(id);
		}
		else
		{
			throw new TrackNotFoundException(); 
		}
	}
	
	public void deleteAllTracks() throws TrackNotFoundException
	{
		if(!getAllTracks().isEmpty())
		{
			trackRepo.deleteAll();
		}
		else
		{
			throw new TrackNotFoundException(); 
		}
	}
	
	public void generateExcel() throws IOException
	{
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet=workBook.createSheet("track info");
		List<Track> tracks=getAllTracks();
		XSSFRow headerRow=sheet.createRow(0);
		XSSFCell cell=headerRow.createCell(0);
		cell.setCellValue("Id");
		cell=headerRow.createCell(1);
		cell.setCellValue("Name");
		cell=headerRow.createCell(2);
		cell.setCellValue("BasedOn");
		int rowCount=1;
		for(Track t:tracks)
		{
			headerRow=sheet.createRow(rowCount++);
			int colCount=0;
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(t.getId());
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(t.getName());
			cell=headerRow.createCell(colCount++);
			cell.setCellValue(t.getBasedOn());
			
		}
		String filePath=".\\datafiles\\tracks.xlsx";
		FileOutputStream fos=new FileOutputStream(filePath);
		workBook.write(fos);
		fos.close();
		Log.info("excel data generated");
		
	}
	
}
