package cs213.photoAlbum.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

	private static final String DATE_FMT = "MM/dd/yyyy-HH:mm:ss";

	public static Calendar toCalendar(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	
	public static Calendar parseDate(String string) throws ParseException {

		Calendar cal = Calendar.getInstance();
		Date dateStr = null;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FMT);
		dateStr = formatter.parse(string);
		cal.setTime(dateStr);
		cal.set(Calendar.MILLISECOND, 0);

		return cal;
	}
	
	public static String toFmtDate(Calendar cal) {
		
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FMT);
		return formatter.format(cal.getTime());
	}

}
