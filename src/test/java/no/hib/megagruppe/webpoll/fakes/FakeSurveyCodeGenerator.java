package no.hib.megagruppe.webpoll.fakes;

import no.hib.megagruppe.webpoll.util.SurveyCodeGenerator;

public class FakeSurveyCodeGenerator implements SurveyCodeGenerator{

	@Override
	public void init() {
		
	}

	@Override
	public String generateSurveyCode(int digits) {
		return "fox" + digits;
	}

}
