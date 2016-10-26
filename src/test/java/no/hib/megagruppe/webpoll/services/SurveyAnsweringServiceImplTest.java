package no.hib.megagruppe.webpoll.services;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.fakes.FakeRepositoryFactory;
import no.hib.megagruppe.webpoll.fakes.TestSurveyRepository;
import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;

public class SurveyAnsweringServiceImplTest {

	/** ! TEMPORARY JAVADOC ! */
	
	/**  SurveyRepository
	 * 
	 * + SurveyEntity add(SurveyEntity entity); 	Adds a SurveyEntity to the repository.
     * + SurveyEntity findById(int id); 			Finds a SurveyEntity by ID.
     * + List<SurveyEntity> findAll();				Returns a list of all SurveyEntities.
     * + SurveyEntity update(SurveyEntity entity);	Updates the SurveyEntity.
     * + void remove(SurveyEntity entity);			Removes the SurveyEntity from the repository.
	 * + findByCode(String code)					Finds a SurveyEntity by its given code.
	 */
	private SurveyRepository surveyRepository;
	
	/** SurveyEntity
	 * 	
	 *  A survey saved in the repository (database).
	 */
	private SurveyEntity survey;
	
	/** SurveyAnsweringService
	 * 
	 * boolean isValidSurvey(String code);
	 * SurveyAnsweringModel startSurveyAnswering(String code);
	 * void commitSurveyAnswering(SurveyAnsweringModel context);
	 * 
	 */
	private SurveyAnsweringService service;
	
	@Before
	public void setup() {
		surveyRepository = new TestSurveyRepository();

		survey = new SurveyEntity();
		survey.setName("Test");
		survey.setCode("abc");
		
		surveyRepository.add(survey);
		service = buildService(surveyRepository);
	}
	
	@Test
	public void isValidSurveyReturnsFalseForNonExistantSurvey() {
		assertFalse(service.isValidSurvey("I do not exit"));
	}

	@Test
	public void isValidSurveyReturnsTrueForActiveSurvey() {
		survey.setActive(true);
		assertTrue(service.isValidSurvey("abc"));
	}

	@Test
	public void isValidSurveyReturnsFalseForInactiveSurvey() {
		survey.setActive(false);
		assertFalse(service.isValidSurvey("abc"));
	}
	
	@Test
	public void startingSurveyGivesCorrectSurveyAnsweringModel() {
		survey.setName("Favorite test");
		survey.setDate(new Date(100000));
		survey.setDeadline(new Date(120000));
		
		SurveyAnsweringModel sam = service.startSurveyAnswering("abc");
		assertEquals(survey.getName(), sam.getSurveyName());
		assertEquals(survey.getDate(), sam.getSurveyDate());
		assertEquals(survey.getDeadline(), sam.getSurveyDeadline());
	}
	
	@Test
	public void commitSurveyAnsweringSavesTheAnswersInRepository(){
		// TODO
		SurveyAnsweringModel sam = service.startSurveyAnswering("abc");
		
		service.commitSurveyAnswering(sam);
	}

	private static SurveyAnsweringService buildService(SurveyRepository surveyRepository) {
		return new SurveyAnsweringServiceImpl(new FakeRepositoryFactory(null, surveyRepository));
	}

}
