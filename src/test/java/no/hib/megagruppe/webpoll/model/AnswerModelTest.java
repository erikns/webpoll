package no.hib.megagruppe.webpoll.model;

import no.hib.megagruppe.webpoll.models.AnswerModel;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class AnswerModelTest {

    /**
     * Empty model for testing
     */
    AnswerModel am = new AnswerModel();

    @Test
    public void hasNextQuestionisFalseByDefault(){
        assertFalse(am.hasNextQuestion());
    }


}
