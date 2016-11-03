package no.hib.megagruppe.webpoll.servlets.lecturer;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.services.SecurityService;

/**
 * Servlet implementation class LecturerSerlvet
 */
@WebServlet("/lecturer")
public class LecturerSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
    SecurityService securityService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(securityService.isLoggedIn()) {
			request.getSession().setAttribute("loggedinuser", securityService.getLoggedInUserName());
			request.getRequestDispatcher("WEB-INF/lecturer/lecturerhome.jsp").forward(request, response);
			
		/*   Dersom vi skal ha en oversikt allerede når foreleser logger inn, må den hentes
			 fra databasen. Dersom oversikt skal være en egen side, må undersøkelsene hentes
			 når knappen på denne siden trykkes på (doPost i en egen LecturerOversiktServlet). */
			
		} else {
			response.sendRedirect("login");
		}	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO
	}
}
