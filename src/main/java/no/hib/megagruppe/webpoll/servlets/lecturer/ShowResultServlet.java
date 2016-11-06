package no.hib.megagruppe.webpoll.servlets.lecturer;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.services.SurveyOverviewService;
import no.hib.megagruppe.webpoll.util.sessionmanager.SeeSurveyOverviewSessionManager;

/**
 * Servlet implementation class ShowResultServlet
 * 
 * Skal vise foreløpig resultat av survey som er startet, eller ferdig resultat.
 * Skal bli sendt til resultat siden etter at survey blir startet, eller gjennom Vis Resultat
 * knapp på SurveyOverview siden. 
 * Må huske å hente id fra survey for å oppdatere resultat dersom surveyen ikkje er ferdig.
 * 
 */
@WebServlet("/surveyresult")
public class ShowResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
    SecurityService securityService;
	
	SurveyOverviewService surveyoverview;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(securityService.isLoggedIn()) {
			SeeSurveyOverviewSessionManager session = new SeeSurveyOverviewSessionManager(request);
			request.getRequestDispatcher("WEB-INF/lecturer/surveyresult.jsp").forward(request, response);
			session.clearErrorMessages();
		} else {
			response.sendRedirect("index");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(securityService.isLoggedIn()) {
			SeeSurveyOverviewSessionManager session = new SeeSurveyOverviewSessionManager(request);
			Integer id = session.getID();
			SurveyEntity survey = session.getSurvey(id);  //Prøver å hente den gjeldende surveyen som er blitt valgt gjennom parameter id
			
		 if ( surveyoverview.isActive(id) ) {             //Ønsker en metode for å se om surveyen er active gjennom parameter id
				
				survey.getResponses();		              //Henter svar fra gjeldende survey
				
				request.getRequestDispatcher("WEB-INF/lecturer/surveyresult.jsp").forward(request, response); //Ønsker å refreshe siden, og da vil alt bli kjørt på nytt, så tror id-en også blir hentet på nytt. Men er ikkje sikker
				
		} else {
				
			survey.getResponses();							//Dersom den ikkje er active, vises svarene uten å refreshe siden
				
			}
			
			
	} else {
		response.sendRedirect("index");
	}

	}
}
