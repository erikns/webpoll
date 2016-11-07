package no.hib.megagruppe.webpoll;

import java.sql.Timestamp;

import no.hib.megagruppe.webpoll.util.sessionmanager.ErrorMessage;
import no.hib.megagruppe.webpoll.util.sessionmanager.SeeSurveyOverviewSessionManager;

public class MainTest {

	public static void main(String[] args) {
		System.out.println(ErrorMessage.NOT_LOGGED_IN);
		SeeSurveyOverviewSessionManager session = new SeeSurveyOverviewSessionManager(null);
		Timestamp t = session.getTimestamp(null, null, "3");
		System.out.println(t);
	}

}
