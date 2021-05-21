package com.mt.mindjpa.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mt.mindjpa.exceptionhandling.MIndNotFoundException;
import com.mt.mindjpa.model.MTMind;

@Service
public interface MindService {

	public MTMind addMind(MTMind mind);
	public List<MTMind> addMinds(List<MTMind> minds);
	public Optional<MTMind> getMindById(int id) throws MIndNotFoundException;
	public List<MTMind> getAllMinds();
	public MTMind updateMind(MTMind mind);
	public void deleteMindById(int id) throws MIndNotFoundException;
	public void deleteAllMinds() throws MIndNotFoundException;
	public void generateExcel() throws IOException;
}
