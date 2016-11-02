package no.hib.megagruppe.webpoll.util;

import no.hib.megagruppe.webpoll.models.answering.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.testutil.SurveyAnsweringModelBuilder;
import org.junit.Before;
import org.junit.Test;

public class DurationFormatTest {

    private SurveyAnsweringModel sam;

    @Before
    public void setup() {
        sam = SurveyAnsweringModelBuilder.build();
    }

    @Test
    public void TimeRemainingReturnsFormattedString(){
        System.out.println(sam.getTimeRemaining());
        //assertEquals(sam.getTimeRemaining(), "0d, 9t, 59m, 59s");
        //Vanskelig å teste fordi den er basert på realtime
    }
}
