package no.hib.megagruppe.webpoll.servlets;

import java.io.IOException;
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
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("usrplaceholder", "Brukernavn");
		request.setAttribute("pswdplaceholder", "Passord");
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("usrplaceholder", "Ugyldig brukernavn!");
		request.setAttribute("pswdplaceholder", "Ugyldig passord!");
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}

}
