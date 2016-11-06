package no.hib.megagruppe.webpoll.util;

public interface SurveyCodeGenerator {
	/**
	 * Generates a surveycode based on a dictionary-file concatenated by a number with specified digits.
	 * @param digits The number of digits that will be used in the code.
	 * @return A surveycode with a word and a number.
	 */
	String generateSurveyCode(int digits);
}