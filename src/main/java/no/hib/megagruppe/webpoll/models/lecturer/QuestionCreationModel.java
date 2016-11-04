package no.hib.megagruppe.webpoll.models.lecturer;

import no.hib.megagruppe.webpoll.entities.QuestionEntity.QuestionType;

/**
 * Model for creating a new question for a survey.
 * @author Magnus
 *
 */
public class QuestionCreationModel {

	/*
	 * Funksjoner:
	 * 	- Velg type
	 * 	- Skriv spørmålstekst
	 * 	- OK (Gå videre)
	 * 		- Dersom det er multiple-choice, gå til ny servlet med 10 tekstfelter for options.
	 * 			- Fra denne trykk OK for å bli sendt tilbake til SurveyCreationModel servlet.
	 * 
	 * 
	 */
	
	private QuestionType questionType;
	private String questionText;
	private String[] options;
	
	public QuestionCreationModel(QuestionType questionType, String questionText) {
		this.questionType = questionType;
		this.questionText = questionText;
	}
	
	/**
	 * Checks if the question is ready to be built.
	 * If it is a multiple-choice question, it must have atleast one option.
	 * If it is not a multiple-choice question then the options-array is empty.
	 * @return True if there is atleast one option in the case of multiple-choice question.
	 */
	public boolean isReady(){
		boolean hasOptionsIfMultipleChoice 
				= questionType.isMultipleChoice() ? numberOfOptionsWritten() >= 1 : options == null;
		
		return hasOptionsIfMultipleChoice;
	}
	
	/**
	 * Gets the number of options in this question.
	 * @return THe number of options for this question.
	 */
	public int numberOfOptionsWritten(){
		int sum = 0;		
		for(String option : options){
			if(option != null && !option.equals("")){
				sum++;
			}
		}
				
		return sum;
	}
	
	
	
	

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	public void setOptions(String[] options){
		this.options = options;
	}
	
	public String[] getOptions(){
		return options;
	}
	
}
