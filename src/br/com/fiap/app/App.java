package br.com.fiap.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.fiap.config.Configuration;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class App {
	public static void main(String[] args) {
		try {
			int twzQtd = 0, reTwtsQtd = 0, favsQtd = 0;
			List<String> users = new ArrayList<String>();
			List<Date> datas = new ArrayList<Date>();
			
			for (Status tweet: contagem("#jvm")) {
				if (tweet.isRetweet()) {
					reTwtsQtd++;
				} else {
					twzQtd++;
				}
				
				favsQtd += tweet.getFavoriteCount();
				users.add(tweet.getUser().getName());
				datas.add(tweet.getCreatedAt());
			}
			
			Collections.sort(users);
			Collections.sort(datas);
			
			// Dados dos tweet's
			System.out.println("Houveram " + twzQtd + " tweets");
			System.out.println("Houveram " + reTwtsQtd + " ReTweets");
			System.out.println("Houveram " + favsQtd + " favoritados");
			
			// Usuarios
			System.out.println("O primeiro autor " + users.get(0));
			System.out.println("O ultimo autor " + users.get(users.size() -1));
			
			// Data
			System.out.println("A data mais antiga " + datas.get(0).toString());
			System.out.println("A data mais atual " + datas.get(users.size() -1).toString());
		} catch (TwitterException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<Status> contagem(String find) throws TwitterException {
		Configuration config = new Configuration();
		Twitter twitter = config.instance();
		
		Query query = new Query(find);
		
		query.setSince(semana(-1));
		query.setUntil(semana(0));
		
		query.setCount(1000);
		QueryResult result = twitter.search(query);
		List<Status> tweets = result.getTweets();
		
		return result.getTweets();
	}
	
	public static String semana(int day) {
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.WEEK_OF_MONTH, day);
		Date data = calendario.getTime();
		String dataPesquisa = new SimpleDateFormat("yyyy-MM-dd").format(data);
		
		return dataPesquisa;
	}
}
