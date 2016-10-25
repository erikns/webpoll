package no.hib.megagruppe.webpoll.util;

import no.hib.megagruppe.webpoll.services.SecurityAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@RequestScoped
public class SessionSecurityAdapter implements SecurityAdapter {
    private final String LOGGED_IN_USER_PARAMETER = "loggedInUser";
    private final String LOGGED_IN_PARAMETER = "loggedIn";

    @Inject
    private HttpSession sessionContext;

    @Override
    public boolean isLoggedIn() {
        Boolean isLoggedIn = (Boolean) sessionContext.getAttribute(LOGGED_IN_PARAMETER);
        if (isLoggedIn != null) {
            return isLoggedIn;
        } else {
            return false;
        }
    }

    @Override
    public int getLoggedInUser() {
        Integer userId = (Integer) sessionContext.getAttribute(LOGGED_IN_USER_PARAMETER);
        if (userId != null) {
            return userId;
        } else {
            return -1;
        }
    }

    @Override
    public boolean logIn(int userId) {
        sessionContext.setAttribute(LOGGED_IN_PARAMETER, true);
        sessionContext.setAttribute(LOGGED_IN_USER_PARAMETER, userId);

        return true;
    }

    @Override
    public void logOut() {
        sessionContext.invalidate();
    }
}
