package no.hib.megagruppe.webpoll.models.answering;

import java.util.ArrayList;
import java.util.List;

import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity.QuestionType;

/**
 * 
 * @author Thomas and Magnus
 * 
 *         This class represents a single question in a survey. It is supposed to be used when filling in information about the
 *         forms in the JSPs for answering questions.
 * 
 *         The QuestionType is either MULTIPLE_CHOICE_CHECKBOX, MULTIPLE_CHOICE_RADIO, or FREE_TEXT.
 *
 */
public class SurveyQuestionModel {

	private String text;
	private QuestionType questionType;
	private List<String> options;
	private String[] answers;

	/**
	 * Constructor
	 * 
	 * @param text
	 *            which makes up the question
	 * @param questionType
	 *            enum - MULTIPLE_CHOICE_CHECKBOX, MULTIPLE_CHOICE_RADIO or FREE_TEXT
	 * @param optionEntities
	 *            specifies the options for the question from 'text'
	 */
	public SurveyQuestionModel(String text, QuestionType questionType, List<OptionEntity> optionEntities) {
		this.text = text;
		this.questionType = questionType;

		this.options = new ArrayList<>();
		if (questionType.isMultipleChoice()) {
			for (OptionEntity oe : optionEntities) {
				this.options.add(oe.getText());
			}
		}
	}

	/**
	 * Helper method to check whether this question is of certain type. Cleaner version of
	 * question.getQuestionType.equals(questionType);
	 * 
	 * @param questionType
	 *            The type it is checking.
	 * @return Whether it is of that type.
	 */
	public boolean isOfType(QuestionType questionType) {
		return this.questionType.equals(questionType);
	}

	/**
	 * Submits a single answer for this question. Use this method only when the questionType is MULTIPLE_CHOICE_RADIO or
	 * FREE_TEXT.
	 *
	 * @param answer
	 *            The answer for this question.
	 */
	public void submitAnswer(String answer) {
		answers = new String[1];
		if(answer != null){
			answers[0] = answer;
		} else {
			answers[0] = "";
		}
	}

	/**
	 * Submits multiple answers for this question. Use this method only when the questionType is MULTIPLE_CHOICE_CHECKBOX.
	 * 
	 * @param answers
	 *            The list of answers for this question.
	 */
	public void submitAnswer(String[] answers) {
		if (answers != null) {
			this.answers = new String[answers.length];
			for (int i = 0; i < answers.length; i++) {
				this.answers[i] = answers[i];
			}
		} else {
			this.answers = new String[0];
		}
	}

	/**
	 * The list of options on this question. If there are no options in this question it returns an empty list.
	 * 
	 * @return The list of options on this question.
	 */
	public List<String> getOptions() {
		return options;
	}

	public String getText() {
		return text;
	}

	public String[] getAnswers() {
		return answers;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setType(QuestionType questionType) {
		this.questionType = questionType;
	}
}
