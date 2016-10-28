package no.hib.megagruppe.webpoll.util;

import java.time.Duration;

public class DurationFormatter {

	final static long DAY_CONVERTION = 86400;
	final static long HOUER_CONVERTION = 3600;
	final static long MINUTE_CONVERSION = 3600;
	final static long SECOND_CONVERSION = 60;

	/**
	 * Formatterer en duration til en String med format 0t, 00m, 39s.
	 * @param duration Duration objektet.
	 * @return En formattert streng.
	 */
	public static String formatDuration(Duration duration) {
	    long seconds = duration.getSeconds();

		String positive = String.format(
	        "%dd, %dt, %02dm, %02ds",
	        getDays(duration),
	        getHouers(duration),
	        getMinutes(duration),
	        getSecondsF(duration));
	    return seconds < 0 ? "-" + positive : positive;
	}


	/**
	 * Getters for formatDuration()
	 */
	private static long getDays(Duration duration){
		Long totalSecs = duration.getSeconds();
		return totalSecs/DAY_CONVERTION;
	}
	private static long getHouers(Duration duration){
		Long totalSecs = duration.getSeconds();
		return (totalSecs % DAY_CONVERTION) / HOUER_CONVERTION;
	}
	private static long getMinutes(Duration duration){
		Long totalSecs = duration.getSeconds();
		return (totalSecs % HOUER_CONVERTION) / SECOND_CONVERSION;
	}
	private static long getSecondsF(Duration duration){
		Long totalSecs = duration.getSeconds();
		return (totalSecs % SECOND_CONVERSION);
	}

}
