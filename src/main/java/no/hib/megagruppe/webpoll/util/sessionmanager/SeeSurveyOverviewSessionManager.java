package no.hib.megagruppe.webpoll.util.sessionmanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SeeSurveyOverviewSessionManager extends SessionManager {

	private final String SURVEY_ID = "surveyid";

	public SeeSurveyOverviewSessionManager(HttpServletRequest request) {
		super(request);
	}

	public Integer getID() {
		HttpSession session = request.getSession();

		return (Integer) session.getAttribute(SURVEY_ID);
	}
	
	public void setID(Integer surveyID) {
		HttpSession session = request.getSession();

		session.setAttribute(SURVEY_ID, surveyID);
	}

}
