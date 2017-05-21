package no.hib.megagruppe.webpoll.models.lecturer;

import java.util.ArrayList;
import java.util.List;


/**
 * Model for creating a new survey.
 * Call createSurvey() when it is ready to be stored in the database.
 * @author Magnus
 *
 */
public class SurveyCreationModel {
	private String name;
	private String owner;
	private List<QuestionCreationModel> questions;
	
	public SurveyCreationModel(String name, String owner){
		this.name = name;
		this.owner = owner;
		questions = new ArrayList<>();
	}

	/**
	 * Adds a question to the survey.
	 * @param question The question to be added.
	 */
	public void addQuestionCreationModel(QuestionCreationModel question){
		questions.add(question);
	}
	
	/**
	 * Removes a question from the survey in the specified position.
	 * @param questionNumber The integer index of the question to be removed.
	 */
	public void removeQuestionAtIndex(int questionNumber){
		questions.remove(questionNumber);
	}
	
	/**
	 * Checks if the survey is ready to be built.
	 * The name must not be empty,
	 * the owner must exist,
	 * and there must be atleast one question.
	 * @return True if name's not empty, owner's not null, and has atleast one question.
	 */
	public boolean isReady(){
		boolean nameReady = name != null && name != ""; // Name can't be empty.
		boolean ownerReady = owner != null; // Must have owner.
		boolean questionsReady = questions.size() >= 1; // Has atleast one question.
		
		return nameReady && ownerReady && questionsReady;
	}
	
	/**
	 * Checks if the SurveyCreationModel has been instantiated.
	 * @return True if it has been instantiated.
	 */
	public boolean isInstantiated() {
		return name != null && !name.equals("") && owner != null && !owner.equals("");
	}

	/**
	 * Checks if the name is not empty. Sets it if it is not empty.
	 * @param newName The new name.
	 * @return True if the name is not empty.
	 */
	public boolean setName(String newName) {
		boolean valid = newName != null && !newName.equals("");
		if(valid){
			this.name = newName;
		}
		return valid;
	}
	
	public String getName() {
		return name;
	}

	public List<QuestionCreationModel> getQuestions() {
		return questions;
	}
}
