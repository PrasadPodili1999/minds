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

import com.mt.mindjpa.model.Lead;
import com.mt.mindjpa.model.MTMind;
import com.mt.mindjpa.service.LeadService;
import com.sun.tools.sjavac.Log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/lead")
public class LeadController {

	@Autowired
	LeadService leadService;
	
	@RequestMapping(value = "/addLead",method = RequestMethod.POST)
	public ResponseEntity<Lead> addLead(@RequestBody Lead lead)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(leadService.addLead(lead));
	}
	
	@RequestMapping(value = "/addLeads",method = RequestMethod.POST)
	public ResponseEntity<List<Lead>> addLeads(@RequestBody List<Lead> leads)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(leadService.addLeads(leads));
	}
	
	@RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
	public ResponseEntity<Optional<Lead>> getLeadById(@PathVariable("id") int id)
	{
		try
		{
			return ResponseEntity.status(HttpStatus.OK).body(leadService.getLeadById(id));
		}
		catch(Exception e)
		{
			Log.error("lead not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<List<Lead>> getAllLeads()
	{
		return ResponseEntity.status(HttpStatus.OK).body(leadService.getAllLeads());
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Lead> updateLead(@RequestBody Lead lead)
	{
		return ResponseEntity.status(HttpStatus.OK).body(leadService.updateLead(lead));
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteLeadById(@PathVariable("id") int id)
	{
		try
		{
			leadService.deleteLeadById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e)
		{
			log.error("lead not available");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllLeads()
	{
		try
		{
			leadService.deleteAllLeads();
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e)
		{
			log.error("lead not available");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public ResponseEntity<Void> generateExcel()
	{
		try {
			leadService.generateExcel();
		} catch (IOException e) {
			log.error("failed to download excel sheet");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
