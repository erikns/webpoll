package no.hib.megagruppe.webpoll.servlets.lecturer;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.util.sessionmanager.LecturerSessionManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private SecurityService securityService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LecturerSessionManager session = new LecturerSessionManager(request);
		request.setAttribute("username", session.getPreviouslyTypedUsername());

		request.getRequestDispatcher("/WEB-INF/lecturer/login.jsp").forward(request, response);
		session.clearErrorMessages();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LecturerSessionManager session = new LecturerSessionManager(request);

		if (securityService.logIn(username, password)) {
			// Login successful
			session.logIn(); // XXX Er denne metoden sikker nok? Se implementasjon.
			response.sendRedirect("lecturer");
		} else {
			// Login failed
			session.setErrorMessage("Feil brukernavn eller passord");
			session.setTypedUsername(username);
			
			response.sendRedirect("login");
		}
	}

}
