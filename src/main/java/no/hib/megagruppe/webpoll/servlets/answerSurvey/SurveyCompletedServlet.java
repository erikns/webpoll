package no.hib.megagruppe.webpoll.servlets.answerSurvey;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.util.sessionmanager.SurveyAnsweringSessionManager;

/**
 * The student is done answering the poll.
 */
@WebServlet("/surveycompleted")
public class SurveyCompletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SurveyAnsweringSessionManager session = new SurveyAnsweringSessionManager(request);

		if (session.hasSurvey()) {
			request.getRequestDispatcher("WEB-INF/survey/surveydone.jsp").forward(request, response);
			
		} else {
			response.sendRedirect("index");
		}
	}
}
