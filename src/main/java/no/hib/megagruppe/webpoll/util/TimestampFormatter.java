package no.hib.megagruppe.webpoll.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampFormatter {

	public static String format(Timestamp deadline){
		SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm");
		Date deadlineDate = new Date(deadline.getTime());
		String formattedDeadline = format.format(deadlineDate);
		return formattedDeadline;
	}
	
}
