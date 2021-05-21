package com.mt.mindjpa.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mt.mindjpa.exceptionhandling.LeadNotFoundException;
import com.mt.mindjpa.model.Lead;

@Service
public interface LeadService {

	public Lead addLead(Lead lead);
	public List<Lead> addLeads(List<Lead> leads);
	public Optional<Lead> getLeadById(int id) throws LeadNotFoundException;
	public List<Lead> getAllLeads();
	public Lead updateLead(Lead lead);
	public void deleteLeadById(int id) throws LeadNotFoundException;
	public void deleteAllLeads() throws LeadNotFoundException;
	public void generateExcel() throws IOException;
}
