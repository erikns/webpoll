package no.hib.megagruppe.webpoll.servlets;

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
			request.setAttribute("loggedinusr", securityService.getLoggedInUserName());
			request.getRequestDispatcher("WEB-INF/lecturerhome.jsp").forward(request, response);
		} else {
			response.sendRedirect("login");
		}	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO
	}
}
