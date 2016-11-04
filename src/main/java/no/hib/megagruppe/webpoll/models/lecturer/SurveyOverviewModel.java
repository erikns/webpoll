package no.hib.megagruppe.webpoll.models.lecturer;

import java.sql.Timestamp;
import java.util.List;

import no.hib.megagruppe.webpoll.entities.ResponseEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;

public class SurveyOverviewModel {
	
	/**
	 * NOTE:
	 * variabler:
	 * 		Responsene fra surveysen
	 * 		Navnet til survey
	 * Metoder:
	 * 		QuestionOverviewModel getResultData();
	 * 		
	 */
	private String name;
	private Timestamp dateCreated;
	private Timestamp deadline;
	private List<QuestionOverviewModel> resultData;
	
	public SurveyOverviewModel(List<QuestionOverviewModel> responses, SurveyEntity survey) {
		name = survey.getName();
		dateCreated = survey.getDateCreated();
		deadline = survey.getDeadline();
		this.resultData = responses;
	}

	public String getName() {
		return name;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public Timestamp getDeadline() {
		return deadline;
	}

	public List<QuestionOverviewModel> getResultData() {
		return resultData;
	}

}
