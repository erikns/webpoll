package no.hib.megagruppe.webpoll.models.lecturer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.util.TimestampFormatter;

/**
 * A class for showing information about a survey, including all the responses and survey-search-code.
 * 
 * @author Jonas
 *
 */
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
	private String formattedDeadline;
	private String formattedCreationTime;
	private List<QuestionOverviewModel> resultData;
	private Long numberOfResponses;
	private String code;
	private boolean activated;
	
	public SurveyOverviewModel(List<QuestionOverviewModel> responses, SurveyEntity survey, Long numberOfResponses) {
		name = survey.getName();
		dateCreated = survey.getDateCreated();
		deadline = survey.getDeadline();
		this.resultData = responses;
		this.numberOfResponses = numberOfResponses;
		this.code = survey.getCode();
	}

	public String getFormattedDeadline(){
		return TimestampFormatter.format(deadline);
	}
	
	public String getFormattedCreationTime(){
		return TimestampFormatter.format(dateCreated);
	}

	public Boolean isPastDeadline() {
		return deadline.before(new Timestamp(new Date().getTime()));
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
	
	public Long getNumberOfResponses(){
		return numberOfResponses;
	}
	
	public String getCode(){
		return code;
	}

	public boolean isActivated() {
		return activated;
	}
}
