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
	
	public QuestionCreationModel(String[] options, boolean canHaveMultipleAnswers, String questionText) {
		this.options = options;
		this.questionType = canHaveMultipleAnswers ? QuestionType.MULTIPLE_CHOICE_CHECKBOX : QuestionType.MULTIPLE_CHOICE_RADIO;
		this.questionText = questionText;
	}
	
	public boolean hasAtleastOneOption(){
		boolean atleastOneOption = false;
		for(String option : options){
			if(option != null && !option.equals("")){
				atleastOneOption = true;
				break;
			}
		}
		return atleastOneOption;
	}
	
	public boolean hasQuestionText(){
		return questionText != null && !questionText.equals("");
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
