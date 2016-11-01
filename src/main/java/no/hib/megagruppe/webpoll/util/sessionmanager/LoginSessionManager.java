package no.hib.megagruppe.webpoll.util.sessionmanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginSessionManager extends SessionManager{

	private final String USERNAME_ATTRIBUTE_NAME = "typedusername";
	
	public LoginSessionManager(HttpServletRequest request) {
		super(request);
	}
	
	
	
	/**
	 * Gets the previously typed username.
	 * @return The previously typed username.
	 */
	public String getPreviouslyTypedUsername(){
		String username;
		HttpSession session = request.getSession();
		
		username = (String)session.getAttribute(USERNAME_ATTRIBUTE_NAME);
		
		return username;
	}
	
	/**
	 * Gets the previously typed username.
	 * @return The previously typed username.
	 */
	public void setTypedUsername(String username){
		HttpSession session = request.getSession();
		
		session.setAttribute(USERNAME_ATTRIBUTE_NAME, username);
	}
	

}
