package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.fakes.FakeRepositoryFactory;
import no.hib.megagruppe.webpoll.fakes.TestSurveyRepository;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class SurveyAnsweringServiceImplTest {

    @Test
    public void isValidSurveyReturnsTrueForActiveSurvey() {
        SurveyRepository surveyRepository = new TestSurveyRepository();

        SurveyEntity survey = new SurveyEntity();
        survey.setName("Test");
        survey.setCode("abc");
        survey.setActive(true);
        surveyRepository.add(survey);

        SurveyAnsweringService service = buildService(surveyRepository);

        assertTrue(service.isValidSurvey("abc"));
    }

    @Test
    public void isValidSurveyReturnsFalseForInactiveSurvey() {
        SurveyRepository surveyRepository = new TestSurveyRepository();

        SurveyEntity survey = new SurveyEntity();
        survey.setName("Test");
        survey.setCode("abc");
        survey.setActive(false);
        surveyRepository.add(survey);

        SurveyAnsweringService service = buildService(surveyRepository);

        assertFalse(service.isValidSurvey("abc"));
    }

    private static SurveyAnsweringService buildService(SurveyRepository surveyRepository) {
        return new SurveyAnsweringServiceImpl(new FakeRepositoryFactory(null, surveyRepository));
    }

}
