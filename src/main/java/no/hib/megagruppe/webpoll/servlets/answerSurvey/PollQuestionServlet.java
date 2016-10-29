package no.hib.megagruppe.webpoll.servlets.answerSurvey;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.models.SurveyQuestionModel;

/*
 * @author Vegard
 * 
 * Forwards current question to pollquestion.jsp, and saves the answers afterwards.
 */
@WebServlet("/pollquestion")
public class PollQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SurveyQuestionModel question;
	private SurveyAnsweringModel poll;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		SurveyAnsweringModel surveyModel = (SurveyAnsweringModel) session.getAttribute("SurveyAnsweringModel");
		
		request.setAttribute("question", surveyModel.getNextQuestion());
		request.setAttribute("hasNextQuestion", surveyModel.hasNextQuestion());
		
		request.getRequestDispatcher("pollquestion.jsp").forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		poll = (SurveyAnsweringModel) request.getAttribute("poll");
		request.getSession().setAttribute("poll", poll);
		
			if (poll.hasNextQuestion()) {
				//More questions in poll, gets next question and sets attribute
				poll.getNextQuestion();
				request.getParameter("question");
				request.getSession().setAttribute("question", question);
			}
				//redirects to SaveAnswerServlet to save current current answer to database
				response.sendRedirect("saveanswerservlet"); 
		}
}