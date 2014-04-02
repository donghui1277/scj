package org.scj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

	public static String Format(String time) {
		String formatString = "";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
		SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");

		try {
			Date date = simpleDateFormat.parse(time);
			Long now = System.currentTimeMillis();
			Long temp = now - date.getTime();
			
			if (temp < 60 * 60 * 1000) {
				formatString = temp/(1000 * 60) + "分钟之前 ";
			} else if(temp < 60 * 60 * 60 * 24 * 1000) {
				formatString = timeDateFormat.format(date) + " ";
			} else {
				formatString = dateFormat.format(date) + " ";
			}
			System.out.println(formatString);
			
		} catch (ParseException px) {
			px.printStackTrace();
		}

		return formatString;
	}
	
}
