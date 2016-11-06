package no.hib.megagruppe.webpoll.servlets.lecturer;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.services.SecurityService;
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
			
		/* if (surveyen er active) {
		 * 
		 * 	while(surveyen er active) {
				
				Vis resultat på siden
				
				Hent ID og refresh siden slik at den blir oppdatert etterhvert som det blir gitt nye svar
				
			}
				}
			else {
				
			Vis resultat på siden
				
			}
			
		*/	
	} else {
		response.sendRedirect("index");
	}

	}
}
