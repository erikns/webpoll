package no.hib.megagruppe.webpoll.servlets.lecturer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CopySurveyServlet
 * 
 * Denne servleten blir forwardet fra en survey oversikt til CreateSurvey eller SurveyBuilder siden 
 * (litt usikker), og oppretter alle spørsmål som denne surveyen inneholdt i en ny survey.
 * 
 */
@WebServlet("/CopySurveyServlet")
public class CopySurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
