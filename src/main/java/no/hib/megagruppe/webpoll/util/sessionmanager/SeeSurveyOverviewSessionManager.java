package no.hib.megagruppe.webpoll.util.sessionmanager;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	
	public boolean hasSurveyID(){
		HttpSession session = request.getSession();

		return session.getAttribute(SURVEY_ID) != null;
	}
	
	public Timestamp getTimestamp(String daysS, String hoursS, String minutesS){
		daysS = daysS==null || daysS.equals("") ? "0" : daysS;
		hoursS = hoursS==null || hoursS.equals("") ? "0" : hoursS;
		minutesS = minutesS==null || minutesS.equals("") ? "0" : minutesS;
		
		Integer days = Integer.parseInt(daysS);
		Integer hours = Integer.parseInt(hoursS);
		Integer minutes = Integer.parseInt(minutesS);
		
		if(days==0 && hours==0 && minutes==0){
			days = 10000;
			hours = 11;
			minutes = 59;
		}
		
		days = days > 10000 ? 10000 : days;
		hours = hours%24;
		minutes = minutes%60;
		
		LocalDateTime localDateTime = LocalDateTime.now();
		localDateTime = localDateTime.plusDays(days);
		localDateTime = localDateTime.plusHours(hours);
		localDateTime = localDateTime.plusMinutes(minutes);
		
		return Timestamp.valueOf(localDateTime);
	}

}
