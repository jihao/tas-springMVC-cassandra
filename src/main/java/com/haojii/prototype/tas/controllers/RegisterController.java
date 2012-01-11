package com.haojii.prototype.tas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haojii.prototype.tas.services.IUserService;

@Controller
public class RegisterController {
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(value = "/register",method = RequestMethod.GET )
    public String register() {
        return "register";
    }

	
	@RequestMapping(value = "/register",method = RequestMethod.POST )
    public String login(@Valid RegisterFormBean form,
    		BindingResult result,
    		RedirectAttributes redirectAttrs) {
        
        if (result.hasErrors()) {
			System.out.println( "Object has validation errors" );
		} else {
			System.out.println( "No errors" );
			
			userService.insertUser(form.getUser());
		}
        
        redirectAttrs.addFlashAttribute("flash_message", "register succeed! continue with login!");
        return "redirect:/login";
    }


}
