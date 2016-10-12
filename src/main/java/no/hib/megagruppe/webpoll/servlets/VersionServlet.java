package no.hib.megagruppe.webpoll.servlets;

import no.hib.megagruppe.webpoll.util.VersionInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to expose the build number of the deployed application
 */
@WebServlet("/version")
public class VersionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain");

        VersionInfo versionInfo = new VersionInfo(getServletContext());
        resp.getWriter().println("Build: " + versionInfo.getBuildNumber());
        resp.getWriter().println("SVN revision: " + versionInfo.getRevision());
    }
}
