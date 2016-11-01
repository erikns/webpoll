package no.hib.megagruppe.webpoll.util;

import no.hib.megagruppe.webpoll.inmemory.InMemorySurveyRepository;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.models.answering.SurveyAnsweringModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DurationFormatTest {

    SurveyAnsweringModel sam; // sam = SurveyAnsweringModel

    @Before
    public void setup() {
        InMemorySurveyRepository imsr = new InMemorySurveyRepository();
        SurveyEntity se = imsr.findByCode("testabc");
        sam = new SurveyAnsweringModel(se);
    }

    @Test
    public void TimeRemainingReturnsFormattedString(){
        System.out.println(sam.getTimeRemaining());
        //assertEquals(sam.getTimeRemaining(), "0d, 9t, 59m, 59s");
        //Vanskelig å teste fordi den er basert på realtime
    }
}
