package no.hib.megagruppe.webpoll.servlets.lecturer;

import java.io.IOException;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;
import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.services.SurveyOverviewService;
import no.hib.megagruppe.webpoll.util.sessionmanager.CreateSurveySessionManager;

/**
 * Servlet implementation class StartSurveyServlet
 * 
 * Skal starte en survey gjennom en Start Survey knapp på SurveyOverview siden. Når denne 
 * kanppen trykkes, skal tiden som er satt inn bli sendt til en service som lagrer tiden,
 * og sender tilbake til StartSurveyServlet.
 * 
 */
@WebServlet("/startsurvey")
public class StartSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
    SecurityService securityService;
	
	SurveyOverviewService surveyoverview;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(securityService.isLoggedIn()) {
			CreateSurveySessionManager session = new CreateSurveySessionManager(request);
			request.getRequestDispatcher("WEB-INF/createsurvey/changename.jsp").forward(request, response);
			session.clearErrorMessages();
			
			/* Må endres til noe annet enn CreateSurveySessionManager */
			
		} else {
			response.sendRedirect("index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(securityService.isLoggedIn()) {
			CreateSurveySessionManager session = new CreateSurveySessionManager(request);
			
			/* Må endres til noe annet enn CreateSurveySessionManager */
			
			Timestamp deadline = (Timestamp) request.getAttribute("deadline");
			
		/*	SurveyEntity survey = session.getSurveyEntity(); 
			
			Integer id = survey.getId();  
			
			surveyoverview.activateSurvey(deadline, id); */
			
			
		} else {
			response.sendRedirect("index");
		}
	}

}
	
		/*CurrentSurvey = survey.getID();
		CurrentSurvey.setTimestamp();*/
				
	


