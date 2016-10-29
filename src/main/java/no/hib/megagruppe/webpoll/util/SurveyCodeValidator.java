package no.hib.megagruppe.webpoll.util;

import no.hib.megagruppe.webpoll.services.SurveyAnsweringService;

/**
 * Util-class for validating a surveycode.
 * @author Magnus
 *
 */
public class SurveyCodeValidator {

	private final String NO_ERROR = "";
	private final String NOT_VALID_CODE_ERROR_MESSAGE = "Ugyldig kode!";
	private final String EMPTY_CODE_ERROR_MESSAGE = "Du må skrive inn en kode.";
	
	String code;
	SurveyAnsweringService sas;
	
	boolean isValid;
	String errorMessage;
	
	public SurveyCodeValidator(String code, SurveyAnsweringService sas){
		this.code = code;
		this.sas = sas;
		
		isValid = false;
		errorMessage = "Code has not yet been validated. Call validate()-method in SurveyCodeValidator.";
	}
	
	/**
	 * Validates that the code is not null, not empty, and that it exists in the repository.
	 */
	public void validate(){
		if (!(code == null) && !(code.equals(""))) {
			if (sas.isValidSurvey(code)) {
				isValid = true;
				errorMessage = NO_ERROR;

			} else {
				errorMessage = NOT_VALID_CODE_ERROR_MESSAGE;
			}
		} else {
			errorMessage = EMPTY_CODE_ERROR_MESSAGE;
		}
	}
	
	public boolean isValidCode(){
		return isValid;
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
}
