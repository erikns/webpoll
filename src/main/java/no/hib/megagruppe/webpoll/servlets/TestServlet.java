package no.hib.megagruppe.webpoll.servlets;

import no.hib.megagruppe.webpoll.services.SecurityService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @EJB
    SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println("Hello world");

        if (securityService.isLoggedIn()) {
            resp.getWriter().println("Logged in!!!");
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
