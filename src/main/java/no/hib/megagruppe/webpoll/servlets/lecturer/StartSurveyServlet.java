package no.hib.megagruppe.webpoll.servlets.lecturer;

import java.io.IOException;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.services.SurveyOverviewService;
import no.hib.megagruppe.webpoll.util.sessionmanager.SeeSurveyOverviewSessionManager;

/**
 * Servlet implementation class StartSurveyServlet
 * 
 * Skal starte(/aktivere) en survey gjennom en "Start Survey" knapp på SurveyOverview siden. Når denne 
 * kanppen trykkes, skal tiden som er satt inn bli sendt til en service som lagrer tiden,
 * og sender tilbake til StartSurveyServlet.
 */
@WebServlet("/startsurvey")
public class StartSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
    SecurityService securityService;
	@EJB
	SurveyOverviewService surveyOverviewService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(securityService.isLoggedIn()) {
			SeeSurveyOverviewSessionManager session = new SeeSurveyOverviewSessionManager(request);
			request.getRequestDispatcher("WEB-INF/lecturer/startsurvey.jsp").forward(request, response);
			session.clearErrorMessages();
		} else {
			response.sendRedirect("index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(securityService.isLoggedIn()) {
			SeeSurveyOverviewSessionManager session = new SeeSurveyOverviewSessionManager(request);
			/*
			 * Kan ikkje sende Timestamp via jsp så må gjøre litt magi for å gjøre om datetime_local i stedet 
			 */
			String datetimeLocal = (String) request.getAttribute("deadline");
		
			if ((datetimeLocal.length() - datetimeLocal.replace(":", "").length()) == 1) {
				datetimeLocal += ":00";
			}
			Timestamp deadline = Timestamp.valueOf(datetimeLocal.replace("T", " "));

			Integer id = session.getID();  
			surveyOverviewService.activateSurvey(deadline, id);
		} else {
			response.sendRedirect("index");
		}
	}
}