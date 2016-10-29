package no.hib.megagruppe.webpoll.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Shows information about the survey. From here the user can start the survey.
 * 
 */
@WebServlet("/pollStart")
public class PollStart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	
    	if(session == null || session.getAttribute("poll") == null){
    		response.sendRedirect("/");
    	} else {
    		request.getRequestDispatcher("/poll.jsp").forward(request, response);
    	}
	}
}
