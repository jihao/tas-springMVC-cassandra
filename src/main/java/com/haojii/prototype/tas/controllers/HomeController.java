package com.haojii.prototype.tas.controllers;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
    @Autowired
    Comparator<String> comparator;

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
	
	@RequestMapping(value = "/compare")
	public String compare(@RequestParam("input1") String input1, @RequestParam("input2") String input2, Model model) {
        System.out.println("HomeController: Passing through...");
		int result = comparator.compare(input1, input2);
        String inEnglish = (result < 0) ? "less than" : (result > 0 ? "greater than" : "equal to");
 
        String output = "According to our Comparator, '" + input1 + "' is " + inEnglish + "'" + input2 + "'";
        System.out.println("output="+output);
        model.addAttribute("output", output);
        
        return "compareResult";
	}


}
