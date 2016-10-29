package no.hib.megagruppe.webpoll.servlets.answerSurvey;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.models.SurveyQuestionModel;
import no.hib.megagruppe.webpoll.util.sessionmanager.SurveyAnsweringSessionManager;

/*
 * @author Vegard
 * 
 * Forwards current question to pollquestion.jsp, and saves the answers afterwards.
 */
@WebServlet("/pollquestion")
public class PollQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SurveyAnsweringSessionManager session = new SurveyAnsweringSessionManager(request);

		if (session.hasSurvey()) {
			SurveyAnsweringModel surveyModel = session.getSurveyAnsweringModel();

			request.setAttribute("question", surveyModel.currentQuestion());
			request.setAttribute("questionNumber", surveyModel.currentQuestionNumber());
			request.setAttribute("hasNextQuestion", surveyModel.hasNextQuestion());
			request.setAttribute("hasPreviousQuestion", surveyModel.hasPreviousQuestion());
			request.getRequestDispatcher("pollquestion.jsp").forward(request, response);
			
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
			
			String action = request.getParameter("action");
			System.out.println(action);
			switch(action){
				case "Neste":
					surveyModel.nextQuestion();
					response.sendRedirect("pollquestion");
					break;
					
				case "Forrige":
					surveyModel.previousQuestion();
					response.sendRedirect("pollquestion");
					break;
					
				case "Finish":
					// Save to database.
					response.sendRedirect("pollcompleted");
					break;
					
				case "Avbryt":
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