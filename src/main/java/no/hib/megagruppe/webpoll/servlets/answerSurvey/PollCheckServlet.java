package no.hib.megagruppe.webpoll.servlets.answerSurvey;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.services.SurveyAnsweringService;
import no.hib.megagruppe.webpoll.util.SurveyAnsweringSessionManager;

/**
 * Servlet implementation class pollStartServlet
 */
@WebServlet("/pollCheck")
public class PollCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private SurveyAnsweringService sas;

	//	@Override
	//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	
	//	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SurveyAnsweringSessionManager session = new SurveyAnsweringSessionManager(request);
		String code = request.getParameter("code");
		
		if (!(code == null) && !(code.equals(""))) {
			if (sas.isValidSurvey(code)) {

				SurveyAnsweringModel surveyAnsweringModel = sas.startSurveyAnswering(code);
				session.setSurveyAnsweringModel(surveyAnsweringModel);
				
				response.sendRedirect("pollstart");

			} else {
				request.setAttribute("errormsg", "Ugyldig kode!");
				request.getRequestDispatcher("/").forward(request, response);
			}
		} else {
			request.setAttribute("errormsg", "Du må skrive inn en kode.");
			request.getRequestDispatcher("/").forward(request, response);
		}
	}
}
