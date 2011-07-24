package com.uaonrails.mining.support;

import java.util.Date;

public class UZTrain {
	public String name;
	public int kupe;
	public int kupe_f;
	public int platzkart;
	public int lux;
	public String dep_str;
	public String arr_str;
	//public String arr_str;
	
	public void updateNum(String type, int num )
	{
		if(type.compareTo("c_1030")==0)
			kupe=num;
		else if(type.compareTo("c_1020")==0)
			platzkart=num;
		else if(type.compareTo("c_1050")==0)
			lux=num;
		else if(type.compareTo("c_1040")==0)
			kupe_f=num;
		
	}
	
}
