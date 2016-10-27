package no.hib.megagruppe.webpoll.dummy;

import no.hib.megagruppe.webpoll.entities.QuestionEntity.QuestionType;
import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.models.SurveyQuestionModel;
import no.hib.megagruppe.webpoll.services.SurveyAnsweringService;

public class DummyService_Example {

	/*
	 * Lager en enkel undersøkelse med to spørsmål:
	 * - Har du noen gang programmert javaEE? (MULTIPLE_CHOICE_RADIO)
	 * 	- Ja
	 * 	- Nei
	 * 
	 * - Hva synes du om WebPoll? (FREE_TEXT)
	 */
	public static void main(String[] args) {

		
		SurveyAnsweringService service = DummySurveyAnsweringServiceFactory.getServiceInstance();
		String surveyCode = DummySurveyAnsweringServiceFactory.getSurveyCode();

		
		
		
		
		if (service.isValidSurvey(surveyCode)) { // Søker etter en survey ved kode.

			
			
			
			
			
			// Har funnet en survey. Starter den fra en ny servlet, derfor trenger man koden hver gang (stateless)-
			SurveyAnsweringModel survey = service.startSurveyAnswering(surveyCode);
			System.out.println("Starter survey...");

			
			
			
			
			
			/*
			 * survey oppfører seg som en iterator og har i hovedsak to metoder.
			 * 	+ hasNextQuestion() : boolean
			 * 	+ getNextQuestion() : SurveyQuestionModel
			 * 
			 * Generer JSP-sidene basert på hver SurveyQuestionModel.
			 * Den inneholder informasjon om spørsmåls-type, alternativer, osv.
			 * 
			 */
			
			
			
			
			int i = 1;
			while (survey.hasNextQuestion()) {
				System.out.println("Spørsmål " + i + ":");

				// Henter neste spørsmål.
				SurveyQuestionModel question = survey.getNextQuestion();

				// Sjekker hvilken type spørsmål det er.
				QuestionType type = question.getQuestionType();
				if (type.equals(QuestionType.FREE_TEXT)) {

					friTekstJSP(question);

				} else if (type.equals(QuestionType.MULTIPLE_CHOICE_CHECKBOX)) {

					multChoiceCheckJSP(question);

				} else if (type.equals(QuestionType.MULTIPLE_CHOICE_RADIO)) {

					multChoiceRadioJSP(question);

				}

				i++;

			}

		}

	}

	/**
	 * Generer en JSP for fritekst spørsmål her.
	 * 
	 * @param q
	 *            Spørsmålet.
	 */
	private static void friTekstJSP(SurveyQuestionModel q) {
		String questionTitle = q.getText();
		System.out.println("Fritekst spørsmål: " + questionTitle + "\n");

		
		
		
		// Når studenten svarer på et spørsmål.
		String studentAnswer = "Min favoritt ting er å skrive, takk for at du spør!";

		
		
		
		// Lagrer svaret i SurveyQuestionModel q.
		q.submitAnswer(studentAnswer);

	}

	/**
	 * Generer en JSP for multiple-choice checkbox svar.
	 * 
	 * @param q
	 *            Spørsmålet.
	 */
	private static void multChoiceCheckJSP(SurveyQuestionModel q) {
		String questionTitle = q.getText();
		System.out.println("Multiple choice (kan velge flere) spørsmål: " + questionTitle);

		
		
		System.out.println("Svaralternativ: ");
		for (String o : q.getOptions()) { // Svaralternativ
			String option = o;
			System.out.println(option);
		}
		System.out.println();

		
		
		// Studenten svarer på en eller flere svaralternativ.
		String[] studentAnswer = { "Taco", "Saltsprengt torsk", "Eple" }; // request.getParameterValues();

		
		
		// Lagrer svaret i SurveyQuestionModel q.
		// NP! Det finnes to metoder inni SurveyQuestionModel for å submitte:
		//	- submit(String)
		//	- submit(String[])
		// submit(String[]) må KUN brukes når det kan være flere valg, sånt som i checkbox-spørsmål.
		q.submitAnswer(studentAnswer);

	}

	/**
	 * Generer en JSP for multiple-choice radio svar.
	 * 
	 * @param q
	 *            Spørsmålet.
	 */
	private static void multChoiceRadioJSP(SurveyQuestionModel q) {
		String questionTitle = q.getText();
		System.out.println("Multiple choice (kan kun velge ett) spørsmål: " + questionTitle);

		
		
		
		System.out.println("Svaralternativ: ");
		for (String o : q.getOptions()) { // Svaralternativ
			String option = o;
			System.out.println(option);
		}
		System.out.println();

		
		
		// Studenten svarer på ett svaralternativ.
		String studentAnswer = "Nei";

		
		
		
		// Lagrer svaret i SurveyQuestionModel q.
		q.submitAnswer(studentAnswer);
	}

}
