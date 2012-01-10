package com.haojii.prototype.tas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	/*
	// this won't work with viewResolver
	@RequestMapping(value = "/")
    public String index() {
        System.out.println("HomeController: Passing through...");
        return "index.jsp";
    }
	*/
	@RequestMapping(value = "/home")
    public String home() {
        System.out.println("HomeController: Passing through...");
        //return "WEB-INF/views/home.jsp";
        return "home";
    }


}
