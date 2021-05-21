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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mt.mindjpa.model.Track;
import com.mt.mindjpa.service.TrackService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/track")
public class TrackController {

	@Autowired
	TrackService trackService;
	
	@RequestMapping(value = "/addTrack", method = RequestMethod.POST)
	public ResponseEntity<Track> addTrack(@RequestBody Track track)
	{
		Track result=trackService.addTrack(track);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@RequestMapping(value = "/addTracks", method = RequestMethod.POST)
	public  ResponseEntity<List<Track>> addTracks(@RequestBody List<Track> tracks)
	{
		List<Track> trackList=(List<Track>)trackService.addTracks(tracks);
		return  ResponseEntity.status(HttpStatus.CREATED).body(trackList);
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public  ResponseEntity<Optional<Track>> getTrackById(@PathVariable("id") int id)
	{
		try
		{
			return ResponseEntity.status(HttpStatus.OK).body(trackService.getTrackById(id));
		}
		catch(Exception e)
		{
			log.error("track not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<List<Track>> getAllTracks()
	{
		return ResponseEntity.status(HttpStatus.OK).body(trackService.getAllTracks());
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Track> updateTrack(@RequestBody Track track)
	{
		return ResponseEntity.status(HttpStatus.OK).body(trackService.updateTrack(track));
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTrackById(@PathVariable("id") int id)
	{
		try
		{
			trackService.deleteTrackById(id);
			log.info("deleted the track with id :"+id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e)
		{
			log.error("track not available");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllTracks()
	{
		try
		{
			trackService.deleteAllTracks();
			log.info("deleted successfully");
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e)
		{
			log.error("track not available");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public ResponseEntity<Void> generateExcel()
	{
		try {
			trackService.generateExcel();
		} catch (IOException e) {
			log.error("failed to download excel sheet");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
