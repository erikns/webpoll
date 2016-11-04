package no.hib.megagruppe.webpoll.models.lecturer;

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
