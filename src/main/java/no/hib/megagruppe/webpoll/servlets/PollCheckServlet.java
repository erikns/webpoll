package no.hib.megagruppe.webpoll.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.services.SurveyAnsweringService;

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
			String code = request.getParameter("code");
			if(!("".equals(code)) || !(code == null)){
				if(sas.isValidSurvey(code)) {
					request.setAttribute("poll", (SurveyAnsweringModel) sas.startSurveyAnswering(code));
					request.getRequestDispatcher("/poll.jsp").forward(request, response);
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
