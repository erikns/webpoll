package no.hib.megagruppe.webpoll.servlets.answerSurvey;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.models.SurveyQuestionModel;
import no.hib.megagruppe.webpoll.services.SurveyAnsweringService;
import no.hib.megagruppe.webpoll.util.sessionmanager.SurveyAnsweringSessionManager;

/*
 * @author Vegard
 * 
 * Forwards current question to pollquestion.jsp, and saves the answers afterwards.
 */
@WebServlet("/surveyquestion")
public class SurveyQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private SurveyAnsweringService sas;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SurveyAnsweringSessionManager session = new SurveyAnsweringSessionManager(request);

		if (session.hasSurvey()) {
			SurveyAnsweringModel surveyModel = session.getSurveyAnsweringModel();

			request.setAttribute("question", surveyModel.currentQuestion());
			request.setAttribute("questionNumber", surveyModel.currentQuestionNumber());
			request.setAttribute("hasNextQuestion", surveyModel.hasNextQuestion());
			request.setAttribute("hasPreviousQuestion", surveyModel.hasPreviousQuestion());
			request.getRequestDispatcher("WEB-INF/survey/surveyquestion.jsp").forward(request, response);
			
		} else {
			response.sendRedirect("index");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SurveyAnsweringSessionManager session = new SurveyAnsweringSessionManager(request);
		
		if (session.hasSurvey()) {
			SurveyAnsweringModel surveyModel = session.getSurveyAnsweringModel();
			SurveyQuestionModel answeredQuestion = surveyModel.currentQuestion();
			session.submitAnswerInAnsweredQuestion(answeredQuestion);
			
			// TODO Er det mulig å bruke enum i jsp filene?
			// Her sjekker vi hvilken knapp brukeren trykket på, og utfører en handling utifra det:
			// Neste spørsmål, Forrige spørsmål, Avbryt besvarelse, Fullfør besvarelse.
			String action = request.getParameter("action");
			switch(action){
				case "next":
					surveyModel.nextQuestion();
					response.sendRedirect("surveyquestion");
					break;
					
				case "previous":
					surveyModel.previousQuestion();
					response.sendRedirect("surveyquestion");
					break;
					
				case "submit":
					sas.commitSurveyAnswering(surveyModel);
					response.sendRedirect("surveycompleted");
					break;
					
				case "cancel":
					session.cancel();
					response.sendRedirect("index");
					break;
					
				default:
					session.cancel();
					response.sendRedirect("index");
			}
			
		} else {
			response.sendRedirect("index");
		}
	}
}