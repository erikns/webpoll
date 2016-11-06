package no.hib.megagruppe.webpoll.servlets.createsurvey;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;
import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.services.SurveyCreationService;
import no.hib.megagruppe.webpoll.util.sessionmanager.CreateSurveySessionManager;
import no.hib.megagruppe.webpoll.util.sessionmanager.ErrorMessage;

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
		
		CreateSurveySessionManager session = new CreateSurveySessionManager(request);
		if(securityService.isLoggedIn()){
			if(session.hasSurveyModel()){
				SurveyCreationModel surveyCreationModel = session.getSurveyModel();
				
				if(surveyCreationModel.isReady()){
					scs.commitSurveyCreation(surveyCreationModel);
					response.sendRedirect("lecturer"); // TODO Legg til skikkelig url.
					session.clearErrorMessages();
				} else {
					session.setErrorMessage(ErrorMessage.SURVEY_NOT_READY_TO_BE_COMMITED);
					response.sendRedirect("surveybuilder");
				}
			} else {
				response.sendRedirect("lecturer");
				session.clearErrorMessages();
			}
			
		} else {
			session.setErrorMessage(ErrorMessage.NOT_LOGGED_IN);
			response.sendRedirect("login");
		}
	}

}
