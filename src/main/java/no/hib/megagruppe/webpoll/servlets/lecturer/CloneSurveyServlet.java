package no.hib.megagruppe.webpoll.servlets.lecturer;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;
import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.services.SurveyOverviewService;
import no.hib.megagruppe.webpoll.util.sessionmanager.CreateSurveySessionManager;
import no.hib.megagruppe.webpoll.util.sessionmanager.ErrorMessage;
import no.hib.megagruppe.webpoll.util.sessionmanager.SeeSurveyOverviewSessionManager;

/**
 * Copies a survey and redirects to surveybuilder.
 */
@WebServlet("/clonesurvey")
public class CloneSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
    SecurityService securityService;
	@EJB
	SurveyOverviewService sos;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SeeSurveyOverviewSessionManager overviewSession = new SeeSurveyOverviewSessionManager(request);
		CreateSurveySessionManager creationSession = new CreateSurveySessionManager(request);
		
		if(securityService.isLoggedIn()) {
			if(overviewSession.hasSurveyID()){
				Integer surveyID = overviewSession.getID();
				SurveyCreationModel surveyCreationModel = sos.cloneSurvey(surveyID);
				creationSession.setSurveyCrationModel(surveyCreationModel);
				response.sendRedirect("surveybuilder");
			} else {
				overviewSession.setErrorMessage(ErrorMessage.NO_SURVEY_IN_SESSION);
			}
		} else {
			overviewSession.setErrorMessage(ErrorMessage.NOT_LOGGED_IN);
			response.sendRedirect("index");
		}
		
	}

}
