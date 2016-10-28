package no.hib.megagruppe.webpoll.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.entities.QuestionEntity.QuestionType;
import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.models.SurveyQuestionModel;

/*
 * @author Torbjørn & Vegard
 * 
 * Saves previous answers to database and redirects to "StudentDoneServlet" when there's
 * no more questions in the poll.
 * 
 */
@WebServlet("/saveanswer")
public class SaveAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SurveyQuestionModel question;
	private SurveyAnsweringModel poll;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		poll = (SurveyAnsweringModel) request.getAttribute("poll");
		request.getSession().setAttribute("poll", poll);
		
		request.getRequestDispatcher("pollquestion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		poll = (SurveyAnsweringModel) request.getAttribute("poll");
		request.getSession().setAttribute("poll", poll);

		if(poll.getPreviousQuestion().getQuestionType().canHaveMultipleAnswers()){ 
			String[] answers = request.getParameterValues("answer"); //array
			question.submitAnswer(answers);
		} else {
			String answer = request.getParameter("answer"); //single answer
			question.submitAnswer(answer);
		}
		
			if (poll.hasNextQuestion()) {
				//More questions in poll, gets next question and sets attribute
				poll.getNextQuestion();
				request.getParameter("question");
				request.getSession().setAttribute("question", question);
				response.sendRedirect("pollquestion");
			} else {
				//No more questions in poll
				response.sendRedirect("studentdoneservlet"); //ikkje laga
	}
}
}