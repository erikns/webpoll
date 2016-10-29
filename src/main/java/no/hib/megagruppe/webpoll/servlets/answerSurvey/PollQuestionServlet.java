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

			request.setAttribute("question", surveyModel.getNextQuestion());
			request.setAttribute("hasNextQuestion", surveyModel.hasNextQuestion());
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
			SurveyQuestionModel answeredQuestion = surveyModel.getPreviouslyAnsweredQuestion();
			session.submitAnswerInAnsweredQuestion(answeredQuestion);
			
			if(surveyModel.hasNextQuestion()){
				response.sendRedirect("pollquestion");
				
			} else {
				// TODO Save response in database.
				
				response.sendRedirect("pollcompleted");
			}
			
		} else {
			response.sendRedirect("index");
		}
	}
}