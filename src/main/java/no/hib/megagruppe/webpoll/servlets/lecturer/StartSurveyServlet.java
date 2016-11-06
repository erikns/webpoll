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
 * 
 * Ting å gjøre as of 05.11.16:
 * 
 * Forandre til riktig session. Er foreløpig bare copypaste fra ChangeNameServlet. fixd
 * 
 * Redirecte til riktig jsp, altså StartSurvey.jsp. fixd
 * 
 * survey.setActive(active) active = true, skal det gjøres her, eller i modellen?
 */
@WebServlet("/startsurvey")
public class StartSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
    SecurityService securityService;
	
	SurveyOverviewService surveyoverview;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(securityService.isLoggedIn()) {
			SeeSurveyOverviewSessionManager session = new SeeSurveyOverviewSessionManager(request);
			request.getRequestDispatcher("WEB-INF/createsurvey/startsurvey.jsp").forward(request, response);
			session.clearErrorMessages();
		} else {
			response.sendRedirect("index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(securityService.isLoggedIn()) {
			SeeSurveyOverviewSessionManager session = new SeeSurveyOverviewSessionManager(request);
			Timestamp deadline = (Timestamp) request.getAttribute("deadline");
			Integer id = session.getID();  
			surveyoverview.activateSurvey(deadline, id); //må kanskje gjøre setActive òg
		} else {
			response.sendRedirect("index");
		}
	}
}