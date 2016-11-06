package no.hib.megagruppe.webpoll.servlets.lecturer;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hib.megagruppe.webpoll.models.lecturer.QuestionAnswerOverviewModel;
import no.hib.megagruppe.webpoll.models.lecturer.QuestionOverviewModel;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyOverviewModel;
import no.hib.megagruppe.webpoll.services.SecurityService;
import no.hib.megagruppe.webpoll.services.SurveyOverviewService;
import no.hib.megagruppe.webpoll.util.sessionmanager.ErrorMessage;
import no.hib.megagruppe.webpoll.util.sessionmanager.SeeSurveyOverviewSessionManager;

/**
 * Servlet implementation class ShowResultServlet
 * 
 * Skal vise foreløpig resultat av survey som er startet, eller ferdig resultat.
 * Skal bli sendt til resultat siden etter at survey blir startet, eller gjennom Vis Resultat
 * knapp på SurveyOverview siden. 
 * Må huske å hente id fra survey for å oppdatere resultat dersom surveyen ikkje er ferdig.
 * 
 */
@WebServlet("/surveyresult")
public class ShowResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
    SecurityService securityService;

	@EJB
	SurveyOverviewService surveyOverviewService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SeeSurveyOverviewSessionManager session = new SeeSurveyOverviewSessionManager(request);
		if(securityService.isLoggedIn()) {
			Integer surveyID = session.getID();
			SurveyOverviewModel surveyOverview = surveyOverviewService.getSurveyOverviewModel(surveyID);
			System.out.println(surveyOverview.getResultData().size());
			for(QuestionOverviewModel q : surveyOverview.getResultData()){
				for(QuestionAnswerOverviewModel a : q.getAnswers()){
					System.out.println(a.getAnswerText());
				}
			}
			
			request.setAttribute("survey", surveyOverview);
			request.getRequestDispatcher("WEB-INF/lecturer/surveyresult.jsp").forward(request, response);
		} else {
			session.setErrorMessage(ErrorMessage.NOT_LOGGED_IN);
			response.sendRedirect("login");
		}
	}
}
