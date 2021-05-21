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
import com.mt.mindjpa.service.MindService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/mind")
public class MindController {

	@Autowired
	MindService mindService;
	
	@RequestMapping(value = "/addMind", method = RequestMethod.POST)
	public ResponseEntity<MTMind> addMind(@RequestBody MTMind mind)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(mindService.addMind(mind));
	}
	
	@RequestMapping(value = "/addMinds", method = RequestMethod.POST)
	public  ResponseEntity<List<MTMind>> addMinds(@RequestBody List<MTMind> minds)
	{
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(mindService.addMinds(minds));
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public  ResponseEntity<Optional<MTMind>> getMindById(@PathVariable("id") int id)
	{
		try
		{
			return ResponseEntity.status(HttpStatus.OK).body(mindService.getMindById(id));
		}
		catch(Exception e)
		{
			log.error("mind not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<List<MTMind>> getAllMinds()
	{
		return ResponseEntity.status(HttpStatus.OK).body(mindService.getAllMinds());
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<MTMind> updateMind(@RequestBody MTMind mind)
	{
		return ResponseEntity.status(HttpStatus.OK).body(mindService.updateMind(mind));
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMindById(@PathVariable("id") int id)
	{
		try
		{
			mindService.deleteMindById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e)
		{
			log.error("mind not available");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllMinds()
	{
		try
		{
			mindService.deleteAllMinds();
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e)
		{
			log.error("mind not available");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public ResponseEntity<Void> generateExcel()
	{
		try {
			mindService.generateExcel();
		} catch (IOException e) {
			log.error("failed to download excel sheet");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
