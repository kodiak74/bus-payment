package com.binarycube.bp.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

	public static String toDateString(Date dt) {
		if (dt == null) return ""; 
		return format.format(dt ); 
	}
	
	public static Date fromDateString(String source) {
		if (source == null) return null;
		try {
			return format.parse(source);
		} catch (ParseException e) {
			//TODO: Integrate logging framework
			e.printStackTrace();
		}
		return null;
	}
	
	
}
