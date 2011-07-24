package com.uaonrails.mining.support;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UZCortege {
	public ArrayList<UZTrain> trains;
	public Date cur_date;
	public Date act_date;
	public String from_;
	public String to_;
	public UZCortege(ArrayList<UZTrain> trains_) {
		trains = trains_;
	}
	
	public void parseDate(String date_)
	{
		  try {
		DateFormat formatter ; 
	   formatter = new SimpleDateFormat("yyyy-MM-dd");
	   	cur_date = new Date();
		act_date = (Date)formatter.parse(date_);
		
		
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	}  
}
