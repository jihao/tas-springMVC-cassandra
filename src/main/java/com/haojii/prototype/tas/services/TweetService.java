package com.haojii.prototype.tas.services;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.haojii.prototype.tas.dao.TweetDAO;
import com.haojii.prototype.tas.dao.TweetDAOCassandraImpl;
import com.haojii.prototype.tas.model.Tweet;

@Service
public class TweetService implements ITweetService {
	private TweetDAO tweetDAO = new TweetDAOCassandraImpl();
	private static Logger logger = Logger.getLogger(TweetService.class);
	
	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.ITweetService#insertTweet(com.haojii.prototype.tas.model.Tweet)
	 */
	@Override
	public boolean insertTweet(Tweet tweet) {
		return tweetDAO.insertTweet(tweet);
	}

	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.ITweetService#deleteTweet(java.lang.String)
	 */
	@Override
	public boolean deleteTweet(String key) {
		return tweetDAO.deleteTweet(key);
	}

	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.ITweetService#findTweet(java.lang.String)
	 */
	@Override
	public Tweet findTweet(String key) {
		return tweetDAO.findTweet(key);
	}

	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.ITweetService#updateTweet(com.haojii.prototype.tas.model.Tweet)
	 */
	@Override
	public boolean updateTweet(Tweet tweet) {
		return tweetDAO.updateTweet(tweet);
	}

	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.ITweetService#listTweets()
	 */
	@Override
	public Collection<Tweet> listTweets() {
		return tweetDAO.listTweets();
	}
	
	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.ITweetService#countTweets()
	 */
	@Override
	public int countTweets() {
		return tweetDAO.countTweets();
	}

}
