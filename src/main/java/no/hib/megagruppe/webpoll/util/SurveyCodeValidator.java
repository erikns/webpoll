package no.hib.megagruppe.webpoll.util;

import no.hib.megagruppe.webpoll.services.SurveyAnsweringService;
import no.hib.megagruppe.webpoll.util.sessionmanager.ErrorMessage;

/**
 * Util-class for validating a surveycode.
 * @author Magnus
 *
 */
public class SurveyCodeValidator {
	
	String code;
	SurveyAnsweringService sas;
	
	boolean isValid;
	ErrorMessage errorMessage;
	
	public SurveyCodeValidator(String code, SurveyAnsweringService sas){
		this.code = code;
		this.sas = sas;
		
		isValid = false;
		errorMessage = null;
	}
	
	/**
	 * Validates that the code is not null, not empty, and that it exists in the repository.
	 */
	public void validate(){
		if (!(code == null) && !(code.equals(""))) {
			if (sas.isValidSurvey(code)) {
				isValid = true;

			} else {
				errorMessage = ErrorMessage.NOT_VALID_CODE_ERROR_MESSAGE;
			}
		} else {
			errorMessage = ErrorMessage.EMPTY_CODE_ERROR_MESSAGE;
		}
	}
	
	public boolean isValidCode(){
		return isValid;
	}
	
	public ErrorMessage getErrorMessage(){
		return errorMessage;
	}
	
}
