package br.com.fiap.config;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Configuration {
	
	public Twitter instance() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("bF3ZBSIiThj1eEwTpnhtM60jF")
		  .setOAuthConsumerSecret("N7LHsn6Nh1GSGKJMBXSQqCDOx2Y6Oi4Z3QMYXrsLoWAX8pqyL7")
		  .setOAuthAccessToken("760553961287974912-sPiLopZDhjUx6ZDqKe5YHLrJfnAM9l9")
		  .setOAuthAccessTokenSecret("B7NhNquVf61V4mEL9rk406vEdDaJKA8Pc7jHR1bOev4Nh");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		return twitter;
	}
}
