package no.hib.megagruppe.webpoll.util;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.Date;

import org.junit.Test;

public class DurationFormatTest {

    @Test
    public void TimeRemainingReturnsFormattedString(){
    	Date now = new Date();
    	Date deadline = new Date(now.getTime() + 10000 + 120000);
    	long remainingMillis = deadline.getTime() - now.getTime();
		Duration timeRemaining = Duration.ofMillis(remainingMillis);
        assertEquals(DurationFormatter.formatDuration(timeRemaining), "0d, 0t, 02m, 10s");
    }
}
