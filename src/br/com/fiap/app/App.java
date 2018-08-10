package br.com.fiap.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.fiap.controllers.TwitterController;
import twitter4j.Status;
import twitter4j.TwitterException;

public class App {
	public static void main(String[] args) {
		// Mostra os tweets
		mostrarTweets();
	}
	
	public static void mostrarTweets() {
		String topic = "#jvm";
		TwitterController tw = new TwitterController(topic);
		tw.getTweetsData();
		
		System.out.println("Tweets por dia");
		for (Map.Entry<String, Integer> map : tw.getTweets().entrySet()) {
			System.out.println(map.getKey() + " : " + map.getValue());
		}
		
		System.out.println(" ---- ");
		
		System.out.println("Retweets por dia");
		for (Map.Entry<String, Integer> map : tw.getRetwetts().entrySet()) {
			System.out.println(map.getKey() + " : " + map.getValue());
		}
		
		System.out.println(" ---- ");
		
		System.out.println("Favoritos por dia");
		for (Map.Entry<String, Integer> map : tw.getFavs().entrySet()) {
			System.out.println(map.getKey() + " : " + map.getValue());
		}
		
		System.out.println(" ---- ");
		System.out.println("Primeiro nome: "+tw.getPessoas().get(0));
		System.out.println("Ultimo nome: "+tw.getPessoas().get(tw.getPessoas().size() - 1));
		
		System.out.println(" ---- ");
		System.out.println("Data menos recente: "+tw.getDatas().get(0));
		System.out.println("Data recente: "+tw.getDatas().get(tw.getDatas().size() - 1));
	}
}
