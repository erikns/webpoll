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
 * Denne servleten kalles rett etter at brukeren har trykket "Ny undersøkelse".
 * Den oppretter en ny SurveyCreationModel og lagrer den i sesjonen.
 * 
 * Deretter redirecter den til SurveyBuilderServlet.
 */
@WebServlet("/instantiatesurvey")
public class InstantiateSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
    SecurityService securityService;
	@EJB
	SurveyCreationService scs;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CreateSurveySessionManager session = new CreateSurveySessionManager(request);
		if(securityService.isLoggedIn()) {
			SurveyCreationModel surveyModel = scs.instantiateNewSurvey();
			session.setSurveyCrationModel(surveyModel);
			response.sendRedirect("surveybuilder");
			session.clearErrorMessages();
		} else {
			session.setErrorMessage(ErrorMessage.NOT_LOGGED_IN);
			response.sendRedirect("index");
		}
	}

}
