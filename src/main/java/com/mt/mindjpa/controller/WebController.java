package com.mt.mindjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping(value = "/")
	public String homePage()
	{
		return "index";
	}
	@RequestMapping(value = "/tracks")
	public String tracksPage()
	{
		return "tracks";
	}
	@RequestMapping(value = "/minds")
	public String mindsPage()
	{
		return "minds";
	}
	@RequestMapping(value = "/leads")
	public String leadsPage()
	{
		return "leads";
	}
	@RequestMapping(value = "/reviews")
	public String reviewsPage()
	{
		return "reviews";
	}
}