package no.hib.megagruppe.webpoll.models.lecturer;

import java.sql.Timestamp;
import java.util.Date;

/**
 * A class for showing basic information about a survey.
 * It does not encapsulate the responses like SurveyOverviewModel do.
 * @author Magnus
 *
 */
public class SurveyBasicInfoModel {

	private Integer id;
	
	private String name;
	private Timestamp dateCreated;
	private Timestamp deadline;
	private boolean activated;
	private Long numberOfResponses;
	private String code;
	

	public SurveyBasicInfoModel(Integer id, String name, Timestamp dateCreated, Timestamp deadline, boolean activated, Long numberOfResponses, String code) {
		this.id = id;
		this.name = name;
		this.dateCreated = dateCreated;
		this.deadline = deadline;
		this.activated = activated;
		this.numberOfResponses = numberOfResponses;
		this.code = code;
	}

	public boolean isPastDeadline() {
		return deadline.before(new Timestamp(new Date().getTime()));
	}

	public Integer getId() {
		return id;
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

	public boolean isActivated() {
		return activated;
	}

	public Long getNumberOfResponses() {
		return numberOfResponses;
	}
	
	public String getCode(){
		return code;
	}
	
	
}
