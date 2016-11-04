package no.hib.megagruppe.webpoll.models.lecturer;

import java.util.List;

import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity.QuestionType;

/**
 * Model for creating a new question for a survey.
 * @author Magnus
 *
 */
public class QuestionCreationModel {
	private QuestionType questionType;
	private String questionText;
	private String[] options;
	
	/**
	 * Constructor for creating freetext question.
	 * @param questionText The question text.
	 */
	public QuestionCreationModel(String questionText) {
		this.questionType = QuestionType.FREE_TEXT;
		this.questionText = questionText;
	}
	
	/**
	 * Constructor for creating multiplechoice question.
	 * @param options Options for the question.
	 * @param canHaveMultipleAnswers Can have multiple answers.
	 * @param questionText The question text.
	 */
	public QuestionCreationModel(String[] options, boolean canHaveMultipleAnswers, String questionText) {
		this.options = options;
		this.questionType = canHaveMultipleAnswers ? QuestionType.MULTIPLE_CHOICE_CHECKBOX : QuestionType.MULTIPLE_CHOICE_RADIO;
		this.questionText = questionText;
	}
	
	/**
	 * Constructor for creating a model based on an QuestionEntity.
	 * @param questionEntity The question enitity.
	 */
	public QuestionCreationModel(QuestionEntity questionEntity) {
		this.questionType = questionEntity.getType();
		this.questionText = questionEntity.getText();
		List<OptionEntity> optionList = questionEntity.getOptions();
		options = new String[optionList.size()];
		for(int i = 0; i < optionList.size(); i++){
			options[i] = optionList.get(i).getText();
		}
	}

	/**
	 * Checks if the question has atleast one option.
	 * @return True if it has atleast one option.
	 */
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
	
	/**
	 * Checks if the question has text associated with it.
	 * @return True if the question has text.
	 */
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