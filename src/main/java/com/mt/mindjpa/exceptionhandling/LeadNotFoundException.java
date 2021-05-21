package com.mt.mindjpa.exceptionhandling;

public class LeadNotFoundException extends Exception{

	public LeadNotFoundException()
	{
		super();
	}
	public LeadNotFoundException(String msg)
	{
		super(msg);
	}
}
