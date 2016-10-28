package no.hib.megagruppe.webpoll.util;

import java.time.Duration;

public class DurationFormatter {

	/**
	 * Formatterer en duration til en String med format 0t, 00m, 39s.
	 * @param duration Duration objektet.
	 * @return En formattert streng.
	 */
	public static String formatDuration(Duration duration) {
	    long seconds = duration.getSeconds();
	    long absSeconds = Math.abs(seconds);
	    String positive = String.format(
	        "%dd, %dt, %02dm, %02ds",
	        absSeconds / 86400,
	        (absSeconds % 24) / 3600,
	        (absSeconds % 3600) / 60,
	        absSeconds % 60);
	    return seconds < 0 ? "-" + positive : positive;
	}
}
