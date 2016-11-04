package no.hib.megagruppe.webpoll.servlets.createsurvey;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;
import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.util.sessionmanager.CreateSurveySessionManager;

/**
 * Endrer navnet til undersøkelsen.
 * Her er det en del logikk som bør flyttes ut av denne klassen.
 */
@WebServlet("/changename")
public class ChangeNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
    SecurityService securityService;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(securityService.isLoggedIn()) {
			request.getRequestDispatcher("WEB-INF/createsurvey/changename.jsp").forward(request, response);
		} else {
			response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check for valid new name. valid ? redirect("/surveybuilder") : redirect("/changename")
		
		if(securityService.isLoggedIn()) {
			CreateSurveySessionManager session = new CreateSurveySessionManager(request);
			
			String newName = request.getParameter("newname");
			
			SurveyCreationModel surveyModel = session.getSurveyModel();
			if(surveyModel.setName(newName)){
				response.sendRedirect("surveybuilder");
			} else {
				session.setErrorMessage("Navnet kan ikke være tomt.");
				response.sendRedirect("changename");
			}
		} else {
			response.sendRedirect("index");
		}
	}

}
