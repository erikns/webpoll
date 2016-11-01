package no.hib.megagruppe.webpoll.models.lecturer;

import java.util.ArrayList;
import java.util.List;

import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity.QuestionType;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;

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
				= questionType.isMultipleChoice() ? options.length >= 1 : options == null;
		
		return hasOptionsIfMultipleChoice;
	}
	
	/**
	 * Returns a QuestionEntity ready to be stored in the database.
	 * Make sure to call isReady() before calling this method.
	 * @param survey The survey that owns this question.
	 * @return The QuestionEntity associated with this SurveyQuestionCreationModel.
	 */
	public QuestionEntity getQuestionEntity(SurveyEntity survey){
		QuestionEntity questionEntity = new QuestionEntity();
		questionEntity.setSurvey(survey);
		questionEntity.setText(questionText);
		questionEntity.setType(questionType);
		questionEntity.setOptions(convertOptionsToEntities(questionEntity));
		
		return questionEntity;
	}
	
	/**
	 * Converts each option into a questionEntity.
	 * It only converts it if the option-string is not empty or null.
	 * @param questionEntity The question owning this option.
	 * @return A list of OptionEntity objects.
	 */
	private List<OptionEntity> convertOptionsToEntities(QuestionEntity questionEntity){
		List<OptionEntity> optionEntities = new ArrayList<>();
		
		for(String option : options){
			boolean optionNotEmpty = option != null && option != "";
			
			if(optionNotEmpty){
				OptionEntity optionEntity = new OptionEntity();
				optionEntity.setText(option);
				optionEntity.setQuestion(questionEntity);
				optionEntities.add(optionEntity);
			}
		}
		
		return optionEntities;
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
	
}
