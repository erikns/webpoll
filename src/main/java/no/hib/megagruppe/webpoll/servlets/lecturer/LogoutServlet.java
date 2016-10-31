package no.hib.megagruppe.webpoll.servlets.lecturer;

import no.hib.megagruppe.webpoll.services.SecurityService;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @EJB
    SecurityService securityService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (securityService.isLoggedIn()) {
            securityService.logOut();
            request.getRequestDispatcher("WEB-INF/lecturer/signedout.jsp").forward(request, response);
        } else {
            response.sendRedirect(getServletContext().getContextPath() + "/");
        }
	}
}
