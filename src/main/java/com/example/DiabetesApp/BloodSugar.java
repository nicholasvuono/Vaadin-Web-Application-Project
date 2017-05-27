package com.example.DiabetesApp;

public class BloodSugar {
	
	private String date;
	private String morning;
	private String noon;
	private String evening;
	
	public BloodSugar(String date, String morning, String noon, String evening){
		this.date = date;
		this.morning = morning;
		this.noon = noon;
		this.evening = evening;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setMorning(String morning){
		this.morning = morning;
	}
	
	public String getMorning(){
		return this.morning;
	}
	
	public void setNoon(String noon){
		this.noon = noon;
	}
	
	public String getNoon(){
		return this.noon;
	}
	
	public void setEvening(String evening){
		this.evening = evening;
	}
	
	public String getEvening(){
		return this.evening;
	}
	
	public String toString(){
		return "Date: " + date + ", Morning: " + morning + ", Noon: " + noon + "Evening: "+ evening;
	}

}
