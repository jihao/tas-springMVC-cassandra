package com.haojii.prototype.tas.services;

import java.util.Collection;

import com.haojii.prototype.tas.model.Tweet;

public interface ITweetService {

	public abstract boolean insertTweet(Tweet tweet);

	public abstract boolean deleteTweet(String key);

	public abstract Tweet findTweet(String key);

	public abstract boolean updateTweet(Tweet tweet);

	public abstract Collection<Tweet> listTweets();

	public abstract int countTweets();

}