package no.hib.megagruppe.webpoll.services;

/**
 * Exposed service interface to the presentation layer for dealing with logging in and out
 */
public interface SecurityService {
    /**
     * Log in a user
     * @param username Username of user to log in
     * @param password Password of user to log in
     * @return True on successful login, false otherwise
     */
    boolean logIn(String username, String password);

    /**
     * Log the currently logged in user out
     */
    void logOut();

    /**
     * Check whether a user is presently logged in
     * @return True if someone is logged in, false otherwise
     */
    boolean isLoggedIn();

    /**
     * Get the user display name that is presently logged in.
     * @return The logged in user display name if someone is logged in, null otherwise
     */
    String getLoggedInUserName();
}
