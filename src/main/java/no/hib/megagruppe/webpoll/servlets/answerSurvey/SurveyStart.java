package no.hib.megagruppe.webpoll.servlets.answerSurvey;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.util.sessionmanager.SurveyAnsweringSessionManager;

/**
 * Shows information about the survey. From here the user can start the survey.
 * 
 */
@WebServlet("/surveystart")
public class SurveyStart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	SurveyAnsweringSessionManager session = new SurveyAnsweringSessionManager(request);
    	
    	if(session.hasSurvey()){
    		request.getRequestDispatcher("WEB-INF/survey/survey.jsp").forward(request, response);
    	} else {
    		response.sendRedirect("index");
    	}
	}
}
