package no.hib.megagruppe.webpoll.servlets.createsurvey;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.models.lecturer.QuestionCreationModel;
import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.util.sessionmanager.CreateSurveySessionManager;
import no.hib.megagruppe.webpoll.util.sessionmanager.ErrorMessage;

/**
 * Oppretter et nytt multiplechoice-spørsmål. Her er det en del logikk som bør flyttes ut av denne klassen.
 */
@WebServlet("/newmultiplechoicequestion")
public class NewMultiplechoiceQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SecurityService securityService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreateSurveySessionManager session = new CreateSurveySessionManager(request);
		if (securityService.isLoggedIn()) {
			if (session.hasSurveyModel()) {
				request.getRequestDispatcher("WEB-INF/createsurvey/createmultiplechoice.jsp").forward(request, response);
			} else {
				response.sendRedirect("lecturer");
				session.clearErrorMessages();
			}
		} else {
			session.setErrorMessage(ErrorMessage.NOT_LOGGED_IN);
			response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check for valid name. valid ? redirect("/surveybuilder") : redirect("/newmultiplechoicequestion")
		// Check for atleast one option.
		CreateSurveySessionManager session = new CreateSurveySessionManager(request);
		
		if (securityService.isLoggedIn()) {
			if (session.hasSurveyModel()) {

				String[] options = request.getParameterValues("option");
				boolean canHaveMultipleAnswers = request.getParameter("canhavemultipleanswers") != null;
				String name = request.getParameter("questionname");
				QuestionCreationModel newQuestion = new QuestionCreationModel(options, canHaveMultipleAnswers, name);

				if (newQuestion.hasQuestionText()) {
					if (newQuestion.hasAtleastOneOption()) {
						session.getSurveyModel().addQuestionCreationModel(newQuestion);
						response.sendRedirect("surveybuilder");
						session.clearErrorMessages();
					} else {
						session.setErrorMessage(ErrorMessage.MULTIPLECHOICE_QUESTION_DOES_NOT_HAVE_ATLEAST_ONE_OPTION);
						response.sendRedirect("newmultiplechoicequestion");
					}
				} else {
					session.setErrorMessage(ErrorMessage.NAME_CAN_NOT_BE_EMPTY);
					response.sendRedirect("newmultiplechoicequestion");
				}
				
			} else {
				response.sendRedirect("lecturer");
				session.clearErrorMessages();
			}
		} else {
			session.setErrorMessage(ErrorMessage.NOT_LOGGED_IN);
			response.sendRedirect("index");
		}
	}

}
