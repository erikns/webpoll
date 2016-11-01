package no.hib.megagruppe.webpoll.models.lecturer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;

public class SurveyCreationModel {

	/* Funksjoner:
	 *	- Gi navn
	 * 	- Nytt spørsmål
	 * 	- Fjern et spørsmål fra listen.
	 * 	- Lagre til database (Dersom har minst 1 spørsmål)
	 * 
	 */

	private SurveyEntity survey;
	
	private String name;
	private UserEntity owner;
	
	private List<QuestionCreationModel> questions;
	
	public SurveyCreationModel(UserEntity owner){
		survey = new SurveyEntity();
		survey.setOwner(owner);
	}
	
	
	
	
	

	
	/**
	 * Adds a question to the survey.
	 * @param question The question to be added.
	 */
	public void addQuestionCreationModel(QuestionCreationModel question){
		questions.add(question);
	}
	
	/**
	 * Removes a question from the survey in the specified position.
	 * @param question The index of the question to be removed.
	 */
	public void removeQuestionCreationModel(int questionNumber){
		questions.remove(questionNumber);
	}
	
	
	
	/**
	 * Checks if the survey is ready to be built.
	 * The name must not be empty,
	 * the owner must exist,
	 * and there must be atleast one question.
	 * @return True if name's not empty, owner's not null, and has atleast one question.
	 */
	public boolean isReady(){
		boolean nameReady = name != null && name != ""; // Name can't be empty.
		boolean ownerReady = owner != null; // Must have owner.
		boolean questionsReady = questions.size() >= 1; // Has atleast one question.
		
		return nameReady && ownerReady && questionsReady;
	}
	
	/**
	 * Creates the SurveyEntity, ready for storing in the database.
	 * Make sure to call isReady() first.
	 * @return The SurveyEntity associated with this model.
	 */
	public SurveyEntity createSurvey(){
		
		survey.setName(name);
		survey.setOwner(owner);
		survey.setActive(false);
		survey.setQuestions(convertQuestionsToEntities());
		
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		survey.setDateCreated(now);
		survey.setCode(generateCode_TemporaryMethod());
		
		return survey;
	}

	/**
	 * @deprecated Temporary
	 * @return The code for the survey, which here will always be 'asdf'.
	 */
	private String generateCode_TemporaryMethod() {
		return "asdf";
	}
	
	/**
	 * Converts the QuestionModel list into an QuestionEntity List.
	 * @return a QuestionEntity List.
	 */
	private List<QuestionEntity> convertQuestionsToEntities(){
		List<QuestionEntity> questionEntities = new ArrayList<>();
		
		for(QuestionCreationModel question : questions){
			QuestionEntity questionEntity = question.getQuestionEntity(survey);
			questionEntities.add(questionEntity);
		}
		
		return questionEntities;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserEntity getOwner() {
		return owner;
	}

	public List<QuestionCreationModel> getQuestions() {
		return questions;
	}
	
}
