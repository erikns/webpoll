package no.hib.megagruppe.webpoll.models.lecturer;

import java.util.ArrayList;
import java.util.List;

import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;


/**
 * Model for creating a new survey.
 * Call createSurvey() when it is ready to be stored in the database.
 * @author Magnus
 *
 */
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
		questions = new ArrayList<>();
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
	 * @param questionNumber The integer index of the question to be removed.
	 */
	public void removeQuestionAtIndex(int questionNumber){
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public List<QuestionCreationModel> getQuestions() {
		return questions;
	}
	
}
