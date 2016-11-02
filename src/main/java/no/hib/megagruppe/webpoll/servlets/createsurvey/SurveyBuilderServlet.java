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
import no.hib.megagruppe.webpoll.util.sessionmanager.CreateSurveySessionManager;

/**
 * Denne servleten forwarder til createsurvey.jsp som fungerer som en main-hub for oppretting av undersøkelser.
 * Post-metoden kalles når et spørsmål slettes fra undersøkelsen.
 */
@WebServlet("/surveybuilder")
public class SurveyBuilderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
    SecurityService securityService;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(securityService.isLoggedIn()) {
			request.getRequestDispatcher("WEB-INF/lecturer/createsurvey.jsp").forward(request, response);
		} else {
			response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Delete question found in request from SurveyModel. Redirect to self, /surveybuilder. 
		
		if(securityService.isLoggedIn()) {
			CreateSurveySessionManager session = new CreateSurveySessionManager(request);
			
			SurveyCreationModel surveyModel = session.getSurveyModel();
			int index = Integer.parseInt(request.getParameter("questionnumber"));
			surveyModel.removeQuestionAtIndex(index);
			
			response.sendRedirect("surveybuilder");
			
		} else {
			response.sendRedirect("index");
		}
	}

}
