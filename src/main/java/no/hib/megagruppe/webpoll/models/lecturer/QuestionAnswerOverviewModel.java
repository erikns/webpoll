package no.hib.megagruppe.webpoll.models.lecturer;

/**
 * A wrapper class for a singular answer in a question containing 3 datavariables:
 * \n-The text of the chosen answer.
 * \n-The frequency that this answer has been chosen.
 * \n-The percentage of this answer being picked, compared to all the other answers in the same question.
 * @author Magnus
 *
 */
public class QuestionAnswerOverviewModel {

	String answerText;
	Integer frequency;
	double percentage;
	
	
	public QuestionAnswerOverviewModel(String answerText, Integer frequency, double percentage) {
		this.answerText = answerText;
		this.frequency = frequency;
		this.percentage = percentage;
	}


	public String getAnswerText() {
		return answerText;
	}


	public Integer getFrequency() {
		return frequency;
	}


	public double getPercentage() {
		return percentage;
	}
	
	
}
