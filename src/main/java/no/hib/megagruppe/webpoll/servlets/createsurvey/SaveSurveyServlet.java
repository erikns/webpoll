package no.hib.megagruppe.webpoll.servlets.createsurvey;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;
import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.services.SurveyCreationService;
import no.hib.megagruppe.webpoll.util.sessionmanager.CreateSurveySessionManager;

/**
 * Lagrer undersøkelsen i databasen.
 */
@WebServlet("/savesurvey")
public class SaveSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@EJB
    SecurityService securityService;
	@EJB
	SurveyCreationService scs;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Save survey in database. Redirect to SurveyInformationServlet.
		if(securityService.isLoggedIn()){
			
			CreateSurveySessionManager session = new CreateSurveySessionManager(request);
			SurveyCreationModel surveyCreationModel = session.getSurveyModel();
			
			if(surveyCreationModel.isReady()){
				scs.commitSurveyCreation(surveyCreationModel);
				response.sendRedirect("SERVLET FOR Å SE INFORMASJON OM UNDERSØKELSE"); // TODO Legg til skikkelig url.
			} else {
				session.setErrorMessage("Du mangler noe i undersøkelsen.");
				response.sendRedirect("surveybuilder");
			}
			
			
		} else {
			response.sendRedirect("login");
		}
	}

}
