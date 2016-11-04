package no.hib.megagruppe.webpoll.models.lecturer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	
	
	public List<QuestionAnswerOverviewModel> getAnswers(){
		List<QuestionAnswerOverviewModel> answers = new ArrayList<>();
		
		for(String key : frequencyTable.keySet()){
			String answerText = key;
			Integer frequency = frequencyTable.get(key);
			double percentage = frequency / frequencySum * 100;
			QuestionAnswerOverviewModel answer = new QuestionAnswerOverviewModel(answerText, frequency, percentage);
			
			answers.add(answer);
		}
		
		return answers;
	}
	
	public String getQuestionText(){
		return questionText;
	}

}
