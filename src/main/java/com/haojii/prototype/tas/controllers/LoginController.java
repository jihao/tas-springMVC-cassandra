package com.haojii.prototype.tas.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haojii.prototype.tas.services.IUserService;

@Controller
public class LoginController {
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(value = "/login",method = RequestMethod.GET )
    public String login() {
        System.out.println("LoginController: Passing through GET login ...");
        return "login";
    }
	@RequestMapping(value = "/logout",method = RequestMethod.GET )
    public String logout(RedirectAttributes redirectAttrs, HttpSession session) {
        System.out.println("LoginController: Passing through GET login ...");
        session.removeAttribute("SESSION_CURRENT_USER");
        redirectAttrs.addFlashAttribute("flash_message", "logout succeed!");
        return "redirect:/home";
    }
	
	@RequestMapping(value = "/login",method = RequestMethod.POST )
    public String login(@Valid LoginFormBean form, 
    		Model model, 
    		BindingResult result,
    		RedirectAttributes redirectAttrs,
    		HttpSession session) {
        System.out.println("LoginController: Passing through POST login ...");
        if (result.hasErrors()) {
			System.out.println( "Object has validation errors" );
		} else {
			System.out.println( "No errors" );
		}
        
        System.out.println(form);
        
        boolean valid = userService.verifyUsernamePassword(form.getUser());
        if (valid) {
	        model.addAttribute("result", "succeed");
	        session.setAttribute("SESSION_CURRENT_USER", form.getUser());
	        redirectAttrs.addFlashAttribute("flash_message", "login succeed! welcome!");
	        return "redirect:/home";
        }
        else {
        	model.addAttribute("result", "failed");
        	redirectAttrs.addFlashAttribute("flash_message", "login failed! please try again!");
        	return "login";
        }
    }

}
