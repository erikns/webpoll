package no.hib.megagruppe.webpoll.servlets.createsurvey;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.data.UserRepository;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.util.sessionmanager.CreateSurveySessionManager;

/**
 * Servlet implementation class InstantiateSurvey
 */
@WebServlet("/instantiatesurvey")
public class InstantiateSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
    SecurityService securityService;
	
	private UserRepository ur;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(securityService.isLoggedIn()) {
			CreateSurveySessionManager session = new CreateSurveySessionManager(request);
			
			UserEntity owner = ur.findByEmail(securityService.getLoggedInUserName());
			session.instantiateNewSurveyModel(owner);
			response.sendRedirect("surveybuilder");
		} else {
			response.sendRedirect("index");
		}
	}

}
