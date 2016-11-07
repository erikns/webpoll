package no.hib.megagruppe.webpoll.servlets.lecturer;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.services.SurveyOverviewService;
import no.hib.megagruppe.webpoll.util.sessionmanager.ErrorMessage;
import no.hib.megagruppe.webpoll.util.sessionmanager.SeeSurveyOverviewSessionManager;

/**
 * Servlet implementation class OpenSurveyServlet
 */
@WebServlet("/opensurvey")
public class OpenSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SecurityService securityService;
	@EJB
	SurveyOverviewService sos;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SeeSurveyOverviewSessionManager session = new SeeSurveyOverviewSessionManager(request);
		
		if(securityService.isLoggedIn()){
			try{
				Integer surveyID = Integer.parseInt(request.getParameter("id"));
				if(sos.ownedByUser(surveyID)){
					Boolean activated = Boolean.parseBoolean(request.getParameter("active"));
					session.setID(surveyID);

					if(activated){
						response.sendRedirect("surveyresult");
					} else {
						response.sendRedirect("startsurvey");
					}
				} else {
					session.setErrorMessage(ErrorMessage.SURVEY_NOT_OWNED_BY_USER);
					response.sendRedirect("lecturer");
				}
				
			}catch(Exception e){
				session.setErrorMessage(ErrorMessage.GENERIC_ERROR);
				response.sendRedirect("lecturer");
			}
			
		} else {
			session.setErrorMessage(ErrorMessage.NOT_LOGGED_IN);
			response.sendRedirect("index");
		}
		
	}

}
