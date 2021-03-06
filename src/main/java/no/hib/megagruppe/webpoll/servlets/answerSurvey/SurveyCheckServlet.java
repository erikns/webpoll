package no.hib.megagruppe.webpoll.servlets.answerSurvey;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.models.answering.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.services.SurveyAnsweringService;
import no.hib.megagruppe.webpoll.util.SurveyCodeValidator;
import no.hib.megagruppe.webpoll.util.sessionmanager.SurveyAnsweringSessionManager;

/**
 * Servlet implementation class pollStartServlet
 */
@WebServlet("/surveycheck")
public class SurveyCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private SurveyAnsweringService sas;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SurveyAnsweringSessionManager session = new SurveyAnsweringSessionManager(request);
		String code = request.getParameter("code");
		
		SurveyCodeValidator codeValidator = new SurveyCodeValidator(code, sas);
		codeValidator.validate();
		
		if(codeValidator.isValidCode()){
			SurveyAnsweringModel surveyAnsweringModel = sas.startSurveyAnswering(code);
			session.setSurveyAnsweringModel(surveyAnsweringModel);
			response.sendRedirect("surveystart");
			
		} else {
			session.setErrorMessage(codeValidator.getErrorMessage());
			response.sendRedirect("index");
		}
	}
}
