package com.mt.mindjpa.exceptionhandling;

public class TrackNotFoundException extends Exception{

	public TrackNotFoundException(String msg)
	{
		super(msg);
	}
	public TrackNotFoundException( )
	{
		super();
	}
}
