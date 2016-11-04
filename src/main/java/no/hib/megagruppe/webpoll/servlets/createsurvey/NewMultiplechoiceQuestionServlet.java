package no.hib.megagruppe.webpoll.servlets.createsurvey;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.entities.QuestionEntity.QuestionType;
import no.hib.megagruppe.webpoll.models.lecturer.QuestionCreationModel;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;
import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.util.sessionmanager.CreateSurveySessionManager;

/**
 * Oppretter et nytt multiplechoice-spørsmål.
 * Her er det en del logikk som bør flyttes ut av denne klassen.
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
		if(securityService.isLoggedIn()) {
			request.getRequestDispatcher("WEB-INF/createsurvey/createmultiplechoice.jsp").forward(request, response);
		} else {
			response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check for valid name. valid ? redirect("/surveybuilder") : redirect("/newmultiplechoicequestion")
		// Check for atleast one option.
		
		if(securityService.isLoggedIn()) {
			CreateSurveySessionManager session = new CreateSurveySessionManager(request);
			
			String newName = request.getParameter("questionname");
			boolean valid = newName != null && !newName.equals(""); // TODO Flytt all logikken for oppretting av spørsmål inn i metode i CreateSurveySessionManager.
			if(valid){
				
				boolean canHaveMultipleAnswers = request.getParameter("canhavemultipleanswers") != null;
				String[] options = request.getParameterValues("option");
				
				boolean hasAtleastOneOption = options.length > 1; // TODO Test at dette fungerer. Fungerer ikke!
				if(hasAtleastOneOption){
					QuestionType questionType = 
							canHaveMultipleAnswers ? QuestionType.MULTIPLE_CHOICE_CHECKBOX : QuestionType.MULTIPLE_CHOICE_RADIO;
					
					QuestionCreationModel question = new QuestionCreationModel(questionType, newName);
					question.setOptions(options);
					
					SurveyCreationModel surveyModel = session.getSurveyModel();
					surveyModel.addQuestionCreationModel(question);
					response.sendRedirect("surveybuilder");
					
				} else {
					
					session.setErrorMessage("Spørsmålet må ha minst ett alternativ.");
					response.sendRedirect("newmultiplechoicequestion");
				}

			} else {
				session.setErrorMessage("Spørsmålsnavnet kan ikke være tomt.");
				response.sendRedirect("newmultiplechoicequestion");
			}
			
		} else {
			response.sendRedirect("index");
		}
	}
	
}
