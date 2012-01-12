package com.haojii.prototype.tas.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haojii.prototype.tas.model.Tweet;
import com.haojii.prototype.tas.model.User;
import com.haojii.prototype.tas.services.ITweetService;

@Controller
public class TweetController {

	@Autowired
	ITweetService tweetService;
	
	@RequestMapping(value = "tweet", method = RequestMethod.GET)
	public String tweet(Model model) {
		System.out.println("TweetController: Passing through ...");
		Collection<Tweet> tweets = tweetService.listTweets();
		model.addAttribute("tweetList", tweets);
		return "tweet";
	}

	@RequestMapping(value="tweets", method=RequestMethod.GET)
    public ModelAndView getTweets() {
		System.out.println("TweetController: Passing through ...");
        ModelAndView mav = new ModelAndView();
        
        Collection<Tweet> tweets = tweetService.listTweets();
        mav.setViewName("tweet");
        mav.addObject("tweetList", tweets);
		
        return mav;
    }
	
	@RequestMapping(value = "/new/tweet", method = RequestMethod.GET)
	public ModelAndView newtweet() {
		System.out.println("TweetController: Passing through ...");
		ModelAndView mav = new ModelAndView();
		
		Collection<Tweet> tweets = tweetService.listTweets();
		mav.setViewName("tweet_json");
		mav.addObject("tweetList", tweets);
		
		return mav;
	}
	@RequestMapping(value="/new/tweet", method=RequestMethod.POST)
    public ModelAndView newTweet(@RequestParam String message, Model model,
			RedirectAttributes redirectAttrs, HttpSession session) {
		System.out.println("TweetController: Passing through ...");
		System.out.println("message = " + message);
		
		Tweet t = new Tweet();
		t.setMessage(message);
		User u = (User)session.getAttribute("SESSION_CURRENT_USER");
		t.setUser(u);
		
		tweetService.insertTweet(t);
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("tweet");
        mav.addObject("result", "success");
        mav.addObject("tweet", t);
		
        return mav;
    }

	@RequestMapping(value = "tweet", method = RequestMethod.POST)
	public String tweet(@RequestParam String message, Model model,
			RedirectAttributes redirectAttrs, HttpSession session) {
		System.out.println("TweetController: Passing through ...");
		System.out.println("message = " + message);
		
		Tweet t = new Tweet();
		t.setMessage(message);
		User u = (User)session.getAttribute("SESSION_CURRENT_USER");
		t.setUser(u);
		
		tweetService.insertTweet(t);
		
		redirectAttrs.addFlashAttribute("flash_message", "tweet succeed!");

		return "redirect:/tweet";
	}

	@RequestMapping("active")
	public @ResponseBody
	List<Tweet> active() {
		return new ArrayList<Tweet>();
	}

	@RequestMapping("inactive")
	public @ResponseBody
	List<Tweet> inactive() {
		return new ArrayList<Tweet>();
	}

}
