package no.hib.megagruppe.webpoll.models.lecturer;

import java.util.HashMap;
import java.util.List;

import no.hib.megagruppe.webpoll.entities.AnswerEntity;
import no.hib.megagruppe.webpoll.entities.ResponseEntity;

public class QuestionOverviewModel {
	
	private String questionText;
	private HashMap<String, Integer> frequencyTable;
	private int frequencySum;
	
	public QuestionOverviewModel(String questionText) {
		this.questionText = questionText;
		frequencySum = 0;
	}
	
	/**
	 * Adds an answer to this question.
	 * @param answer The answer to be added.
	 */
	public void addAnswer(String answer){
		Integer frequency = frequencyTable.get(answer);
		if(frequency == null){
			frequencyTable.put(answer, 1);
		} else {
			frequencyTable.put(answer, frequency+1);
		}
		
		frequencySum++;
	}

}
