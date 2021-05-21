package com.mt.mindjpa.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mt.mindjpa.exceptionhandling.TrackNotFoundException;
import com.mt.mindjpa.model.Track;

@Service
public interface TrackService {

	public Track addTrack(Track track);
	public List<Track> addTracks(List<Track> tracks);
	public Optional<Track> getTrackById(int id) throws TrackNotFoundException;
	public List<Track> getAllTracks();
	public Track updateTrack(Track track);
	public void deleteTrackById(int id) throws TrackNotFoundException;
	public void deleteAllTracks() throws TrackNotFoundException;
	public void generateExcel() throws IOException;
}
