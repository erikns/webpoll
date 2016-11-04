package no.hib.megagruppe.webpoll.servlets.lecturer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowResultServlet
 * 
 * Skal vise foreløpig resultat av survey som er startet, eller ferdig resultat.
 * Skal bli sendt til resultat siden etter at survey blir startet, eller gjennom Vis Resultat
 * knapp på SurveyOverview siden. 
 * Må huske å hente id fra survey for å oppdatere resultat dersom surveyen ikkje er ferdig.
 * 
 */
@WebServlet("/ShowResultServlet")
public class ShowResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
