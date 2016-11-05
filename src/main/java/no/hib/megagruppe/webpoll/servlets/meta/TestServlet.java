package no.hib.megagruppe.webpoll.servlets.meta;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.services.SurveyAnsweringService;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
    SecurityService securityService;

    @EJB
    SurveyAnsweringService surveyAnsweringService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println("Hello world");

        if (securityService.isLoggedIn()) {
            resp.getWriter().println("Logged in!!!");

            if (surveyAnsweringService.isValidSurvey("testabc")) {
                resp.getWriter().println("Survey is active!");
            } else {
                resp.getWriter().println("Survey is NOT active!");
            }
        } else {
            resp.getWriter().println("Not logged in!");
            resp.getWriter().println("<form method=\"post\">");
            resp.getWriter().println("Password: <input type=\"password\" name=\"password\">");
            resp.getWriter().println("<input type=\"submit\" value=\"Log in\">");
            resp.getWriter().println("</form>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pwd = req.getParameter("password");

        securityService.logIn("admin@stud.hib.no", pwd);

        resp.sendRedirect("test");
    }
}
