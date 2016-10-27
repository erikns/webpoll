package no.hib.megagruppe.webpoll.servlets;

import no.hib.megagruppe.webpoll.services.SecurityService;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @EJB
    private SecurityService securityService;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            request.setAttribute("username", username);
        }

        String loginError = (String) request.getSession().getAttribute("loginError");
        if (loginError != null) {
            request.setAttribute("errormsg", loginError);
        }

		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (securityService.logIn(username, password)) {
            // Login successful
            response.sendRedirect(getServletContext().getContextPath() + "/lecturerhome.jsp");
        } else {
            // Login failed
            request.getSession().setAttribute("loginError", "Feil brukernavn eller passord");
            request.getSession().setAttribute("username", username);
            response.sendRedirect(getServletContext().getContextPath() + "/login");
        }
	}

}
