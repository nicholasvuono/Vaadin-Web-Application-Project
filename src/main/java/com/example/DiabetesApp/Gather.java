package com.example.DiabetesApp;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Gather {
	
	public static ArrayList<String> dates = new ArrayList<String>();
	public static ArrayList<String> readings = new ArrayList<String>();
	public static ArrayList<BloodSugar> objects = new ArrayList<BloodSugar>();
	public static ArrayList<Integer> averages = new ArrayList<Integer>();
	
	public static void main(String[] args){
		
	}
	
	public static void getInformation(String url) throws IOException{
		
		
		Document doc = Jsoup.connect(url).get();
		Elements tr = doc.select("tr");
		
		for(Element e : tr.select("td.s1")){
			dates.add(e.text());
		}
		
		for(Element e : doc.select("tr > td.s2")){
			readings.add(e.text());
		}
		
	}
	
	public static void createObject(){
		
		int a = 0;
		int b = 0;
		int c = 1;
		int d = 2;
		
		while(a<dates.size()){ 
			
			objects.add(new BloodSugar(dates.get(a), readings.get(b), readings.get(c), readings.get(d)));
			
			a++;
			b = b+3;
			c = c+3;
			d = d+3;
		}
		
	}
	
	public static void getAverages(){
		
		int a = 0;
		int b = 0;
		int c = 1;
		int d = 2;
		
		while(a<dates.size()){ 
			
			averages.add((Integer.parseInt(readings.get(b)) + Integer.parseInt(readings.get(c)) + Integer.parseInt(readings.get(d)))/3);
			
			a++;
			b = b+3;
			c = c+3;
			d = d+3;
		}
		
	}
	
}
