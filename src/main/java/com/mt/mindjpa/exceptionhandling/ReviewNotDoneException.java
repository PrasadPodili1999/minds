package com.mt.mindjpa.exceptionhandling;

public class ReviewNotDoneException extends Exception{

	public ReviewNotDoneException(String msg)
	{
		super(msg);
	}
	public ReviewNotDoneException()
	{
		super();
	}
}
