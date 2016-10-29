package no.hib.megagruppe.webpoll.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.util.sessionmanager.SurveyAnsweringSessionManager;

/**
 * Forwards to index.jsp
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SurveyAnsweringSessionManager session = new SurveyAnsweringSessionManager(request);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		session.clearErrorMessages();
		
	}
}
