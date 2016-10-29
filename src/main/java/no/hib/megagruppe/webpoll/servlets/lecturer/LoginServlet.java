package no.hib.megagruppe.webpoll.servlets.lecturer;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.util.sessionmanager.LoginSessionManager;

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
		
		LoginSessionManager session = new LoginSessionManager(request);
		request.setAttribute("username", session.getPreviouslyTypedUsername());

		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		session.clearErrorMessages();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (securityService.logIn(username, password)) {
			// Login successful
			response.sendRedirect("lecturer");
		} else {
			// Login failed
			LoginSessionManager session = new LoginSessionManager(request);
			session.setErrorMessage("Feil brukernavn eller passord");
			session.setTypedUsername(username);
			
			response.sendRedirect("login");
		}
	}

}
