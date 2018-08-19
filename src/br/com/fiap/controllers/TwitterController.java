package br.com.fiap.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import br.com.fiap.config.Configuration;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterController {
	private String topic;
	private Twitter twitter;
	HashMap<String, Integer> tweets = new HashMap<String, Integer>();
	HashMap<String, Integer> retwetts = new HashMap<String, Integer>();
	HashMap<String, Integer> favs = new HashMap<String, Integer>();
	ArrayList<String> pessoas = new ArrayList<String>();
	ArrayList<String> datas = new ArrayList<String>();

	public TwitterController(String topic) {
		this.topic = topic;
		this.twitter = Configuration.instance();
	}

	// Seta a semana para pesquisa
	public static String semana(int day) {
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.WEEK_OF_MONTH, day);
		Date data = calendario.getTime();
		String dataPesquisa = new SimpleDateFormat("yyyy-MM-dd").format(data);
		
		return dataPesquisa;
	}
	
	public SimpleDateFormat formataData() {
		Date data = new Date();
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("dd/MM");
		
		return format;
	}
	
	public List<Status> contagem() throws TwitterException {
		Query query = new Query(this.topic);
		query.setSince(semana(-1));
		query.setCount(10000);
		
		QueryResult result = this.twitter.search(query);
		List<Status> tweets = result.getTweets();
		
		return tweets;
	}
	
	public void getTweetsData() {
		SimpleDateFormat format = this.formataData();
		try {
			for (Status tweet : this.contagem()) {
				Date data = tweet.getCreatedAt();
				
				if (!tweet.isRetweet() && !tweet.isFavorited() ) {
					// Tweets do dia
					if (this.tweets.containsKey(format.format(data))) {
						this.tweets.put(format.format(data), this.tweets.get(format.format(data)) + 1);
					} else {
						this.tweets.put(format.format(data), 1);
					}
				}else if (!tweet.isFavorited() && tweet.isRetweet()){
					// Retweets do dia
					if (this.retwetts.containsKey(format.format(data))) {
						this.retwetts.put(format.format(data), this.retwetts.get(format.format(data)) + 1);
					} else {
						this.retwetts.put(format.format(data), 1);
					}
				}
				
				if (tweet.getFavoriteCount() > 0) {
					// Favoritados
					if (this.favs.containsKey(format.format(data))) {
						this.favs.put(format.format(data), this.favs.get(format.format(data)) + tweet.getFavoriteCount());
					} else {
						this.favs.put(format.format(data), tweet.getFavoriteCount());
					}
				}
					
				
				// Nome do autor
				this.pessoas.add(tweet.getUser().getName());
				this.datas.add(format.format(data));
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getDatas() {
		Collections.sort(this.datas);
		return this.datas;
	}

	public HashMap<String, Integer> getTweets() {
		return tweets;
	}

	public void setTweets(HashMap<String, Integer> tweets) {
		this.tweets = tweets;
	}

	public HashMap<String, Integer> getRetwetts() {
		return retwetts;
	}

	public void setRetwetts(HashMap<String, Integer> retwetts) {
		this.retwetts = retwetts;
	}
	
	public HashMap<String, Integer> getFavs() {
		return favs;
	}

	public void setFavs(HashMap<String, Integer> favs) {
		this.favs = favs;
	}
	
	public ArrayList<String> getPessoas() {
		Collections.sort(pessoas);
		return pessoas;
	}

	public void setPessoas(ArrayList<String> pessoas) {
		this.pessoas = pessoas;
	}
}
