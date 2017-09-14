package com.sample;

import java.util.Calendar;
import java.util.Date;

public class Utils {
	
	public static int monthDifference(Date startDate, Date endDate) {
		Calendar sDate = Calendar.getInstance();
		Calendar eDate = Calendar.getInstance();
		sDate.setTime(startDate);
		eDate.setTime(endDate);
		int difInMonths = sDate.get(Calendar.MONTH) - eDate.get(Calendar.MONTH);
		return difInMonths;
	}
}
