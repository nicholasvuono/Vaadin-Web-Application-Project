package com.example.DiabetesApp;

import static com.googlecode.charts4j.Color.*;

import java.text.DecimalFormat;
import java.util.logging.Logger;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Slice;

public class Create {
	
	public static void main(String[] args){
		
	}
	
	public static String EvaluationChart(){
		
		int low = 0;
		int good = 0;
		int okay = 0;
		int poor = 0;
		
		for(int i = 0; i < Gather.averages.size(); i++){
			
			if (Gather.averages.get(i) >= 70 && Gather.averages.get(i) <=110){
				good++;
			}
			
			if (Gather.averages.get(i) > 110 && Gather.averages.get(i) <= 150){
				okay++;
			}
			
			if (Gather.averages.get(i) > 150){
				poor++;
			}
			
			if (Gather.averages.get(i) < 70){
				low++;
			}
		}
		
		double avgL = 100*low/30;
		double avgG = 100*good/30;
		double avgO = 100*okay/30;
		double avgP = 100*poor/30 ;
		
		Slice s1 = Slice.newSlice(low, LAVENDER, "", "low" + "[" + avgL + "%]");
		Slice s2 = Slice.newSlice(good, LIGHTBLUE, "", "good" + "[" + avgG + "%]");
		Slice s3 = Slice.newSlice(okay, WHEAT, "", "okay" + "[" + avgO + "%]");
		Slice s4 = Slice.newSlice(poor, LIGHTSALMON, "", "poor" + "[" + avgP + "%]");
		
		PieChart pie = GCharts.newPieChart(s1, s2, s3, s4);
		pie.setThreeD(false);
		pie.setSize(300, 300);
		pie.setURLEndpoint("//chart.googleapis.com/chart");
		String pieChartUrl = pie.toURLString();
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(pieChartUrl);
		
		return pieChartUrl;
	}
	
	public static String DailyAverageChart(){
		
		BarChartPlot e1 = Plots.newBarChartPlot(Data.newData(Gather.averages.get(0)/2.5, Gather.averages.get(1)/2.5, Gather.averages.get(2)/2.5,
				Gather.averages.get(3)/2.5, Gather.averages.get(4)/2.5, Gather.averages.get(5)/2.5, Gather.averages.get(6)/2.5, Gather.averages.get(7)/2.5,
				Gather.averages.get(8)/2.5, Gather.averages.get(9)/2.5, Gather.averages.get(10)/2.5, Gather.averages.get(11)/2.5, Gather.averages.get(12)/2.5,
				Gather.averages.get(13)/2.5, Gather.averages.get(14)/2.5, Gather.averages.get(15)/2.5, Gather.averages.get(16)/2.5,
				Gather.averages.get(17)/2.5, Gather.averages.get(18)/2.5, Gather.averages.get(19)/2.5, Gather.averages.get(20)/2.5, Gather.averages.get(21)/2.5,
				Gather.averages.get(22)/2.5, Gather.averages.get(23)/2.5, Gather.averages.get(24)/2.5, Gather.averages.get(25)/2.5, Gather.averages.get(26)/2.5,
				Gather.averages.get(27)/2.5, Gather.averages.get(28)/2.5, Gather.averages.get(29)/2.5), LIGHTSLATEGRAY);
	    BarChart bar = GCharts.newBarChart(e1);
	   
	    AxisStyle axisStyle = AxisStyle.newAxisStyle(BLACK, 13, AxisTextAlignment.CENTER);
	    AxisLabels employee = AxisLabelsFactory.newAxisLabels("Day", 50.0);
	    employee.setAxisStyle(axisStyle);
	    AxisLabels callVol = AxisLabelsFactory.newAxisLabels("Avarage (mg/dl)", 50.0);
	    callVol.setAxisStyle(axisStyle);

	    bar.addXAxisLabels(AxisLabelsFactory.newAxisLabels("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
	    		"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"));
	    bar.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 250, 50));
	    bar.addYAxisLabels(callVol);
	    bar.addXAxisLabels(employee);

	    bar.setSize(700, 175);
	    bar.setBarWidth(10);
	    bar.setGrid(100.0, 19.999, 100, 0);
	    bar.setURLEndpoint("//chart.googleapis.com/chart");
	    String barChartUrl = bar.toURLString();
	    
	    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(barChartUrl);
		
		return barChartUrl;
	}
	
	public static String mgdl(){
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		int number = 0;
		
		for(int l=0; l<Gather.readings.size(); l++){
			number = number + Integer.parseInt(Gather.readings.get(l));
		}
		
		double average = (double) (number/Gather.readings.size());
		
		double mgdl = ((46.7 + average) / 28.7);
		
		return df.format(mgdl);
	}
	
	public static char grade(){
		
		char grade = 0;
		int number = 0;
		
		for(int l=0; l<Gather.readings.size(); l++){
			number = number + Integer.parseInt(Gather.readings.get(l));
		}
		
		double average = (double) (number/Gather.readings.size());
		
		double mgdl = ((46.7 + average) / 28.7);
		
		if (mgdl<6.0 && mgdl>5.0){
			grade = 'A';
		}
		
		if (mgdl<7.0 && mgdl>6.0){
			grade = 'B';
		}
		
		if (mgdl<8.0 && mgdl>7.0){
			grade = 'C';
		}
		if (mgdl>8.0){
			grade = 'F';
		}
		
		return grade;
	}

}
