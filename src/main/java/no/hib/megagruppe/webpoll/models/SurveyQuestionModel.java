package no.hib.megagruppe.webpoll.models;

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
 *         The QuestionType is either MULTIPLE_CHOICE_CHECKBOX, MULTIPLE_CHOICE_RADIO, or FREE_TEXT - all corresponding to the
 *         type of
 *
 */
public class SurveyQuestionModel {
	
	private String text;
	private QuestionType questionType;
	private List<String> options;
	private String[] answers;

	private int answersCounter;

	
	// Constructor
	public SurveyQuestionModel(String text, QuestionType questionType, List<OptionEntity> optionEntities) {
		this.text = text;
		this.questionType = questionType;

		this.options = new ArrayList<String>();
		if (questionType.isMultipleChoice()) {
			for (OptionEntity oe : optionEntities) {
				this.options.add(oe.getText());
			}
		}

		// Instantiate answers array.
		if (questionType.canHaveMultipleAnswers()) {
			answers = new String[options.size()];
		} else {
			answers = new String[1];
		}

		answersCounter = 0;

	}

	/**
	 * Submits a single answer for this question. 
	 * Use this method only when the questionType is MULTIPLE_CHOICE_RADIO or FREE_TEXT.
	 *
	 * @param answer
	 *            The answer for this question.
	 */
	public void submitAnswer(String answer) {
		answers[0] = answer;
	}

	/**
	 * Submits multiple answers for this question. 
	 * Use this method only when the questionType is MULTIPLE_CHOICE_CHECKBOX.
	 * 
	 * @param answers
	 *            The list of answers for this question.
	 */
	public void submitAnswer(String[] answers) {
		for (String answer : answers) {
			this.answers[answersCounter] = answer;
			answersCounter++;
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
