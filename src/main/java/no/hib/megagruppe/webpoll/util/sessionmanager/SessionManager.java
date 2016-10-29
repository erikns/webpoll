package no.hib.megagruppe.webpoll.util.sessionmanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * A class for working with sessions.
 * In reality it is a wrapper for HttpServletRequest.
 */
public abstract class SessionManager {

	protected final String ERROR_MESSAGE_ATTRIBUTE_NAME = "errormsg";
	
	protected HttpServletRequest request;
	
	public SessionManager(HttpServletRequest request){
		this.request = request;
	}
	
	/**
	 * Stores an error message in this session.
	 * @param errorMessage The error message.
	 */
	public void setErrorMessage(String errorMessage){
		HttpSession session = request.getSession();
		
		session.setAttribute(ERROR_MESSAGE_ATTRIBUTE_NAME, errorMessage);
	}
	
	/**
	 * Clears the error messages for this session.
	 */
	public void clearErrorMessages(){
		HttpSession session = request.getSession();
		
		session.setAttribute(ERROR_MESSAGE_ATTRIBUTE_NAME, null);
	}
	
	/**
	 * Checks if this session is not null. Checks if this session exists.
	 * @return True if this session is not null;
	 */
	public boolean isNotNullSession(){
		HttpSession session = request.getSession();
		
		return session != null;
	}
	
}
