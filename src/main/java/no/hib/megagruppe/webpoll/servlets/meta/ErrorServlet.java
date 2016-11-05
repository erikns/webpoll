package no.hib.megagruppe.webpoll.servlets.meta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message;
        String headline;

        int errorCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        switch (errorCode) {
            case 404:
                headline = "Hvor er skjorta mi? :(";
                message = "Serveren kan ikke finne ressursen som ble bedt om";
                break;
            default:
                headline = "Huffda :(";
                message = "Serveren kan ha fått en liten forkjølelse";
        }

        req.setAttribute("errorHeading", headline);
        req.setAttribute("errorMessage", message);
        req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
    }
}
