package ch.issueman.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Useful Methods to format complex datetypes.
 *
 * @author Erwin Willi, 
 * @version 1.0.0
 * @since 1.0.0
 */
public class FormatHelper {
	
	/**
	 * format complex datetypes date
	 * 
	 * @param calendar the calendar value to format.
	 * @return formatted date.
	 */
	public static String formatDate(Calendar calendar){
		return (new SimpleDateFormat(ConfigHelper.getConfig("format.date", "dd.MM.yyyy"))).format(calendar.getTime());
	}
	
	/**
	 * format complex datetypes date and time
	 * 
	 * @param calendar the calendar value to format.
	 * @return formatted date and time.
	 */
	public static String formatDateTime(Calendar calendar){
		return (new SimpleDateFormat(ConfigHelper.getConfig("format.datetime", "dd.MM.yyyy hh:ss"))).format(calendar.getTime());
	}
	
}
