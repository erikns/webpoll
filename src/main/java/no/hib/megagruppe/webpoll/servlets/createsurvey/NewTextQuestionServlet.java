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
 * Servlet implementation class NewTextQuestionServlet
 */
@WebServlet("/newtextquestion")
public class NewTextQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
    SecurityService securityService;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(securityService.isLoggedIn()) {
			request.getRequestDispatcher("WEB-INF/lecturer/newtextquestion.jsp").forward(request, response);
		} else {
			response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Check for valid name. valid ? redirect("/surveybuilder") : redirect("/newtextquestion")
		
		if(securityService.isLoggedIn()) {
			CreateSurveySessionManager session = new CreateSurveySessionManager(request);
			
			String newName = request.getParameter("questionname");
			boolean valid = newName != null && !newName.equals(""); // TODO encapsulate logic?
			if(valid){
				SurveyCreationModel surveyModel = session.getSurveyModel();
				QuestionCreationModel question = new QuestionCreationModel(QuestionType.FREE_TEXT, newName);
				surveyModel.addQuestionCreationModel(question);
				response.sendRedirect("surveybuilder");
			} else {
				session.setErrorMessage("Spørsmålsnavnet kan ikke være tomt.");
				response.sendRedirect("newtextquestion");
			}
			
		} else {
			response.sendRedirect("index");
		}
		
	}

}
