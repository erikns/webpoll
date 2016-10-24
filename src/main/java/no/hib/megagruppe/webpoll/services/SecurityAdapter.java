package no.hib.megagruppe.webpoll.services;

/**
 * Interface serving as a layer of indirection between the services layer and the Servlets API in the
 * presentation layer, to avoid any dependencies from the services layer to the presentation layer on top.
 * Concrete realisations of this interface will be passed from the presentation layer to the services layer
 * for integration purposes.
 */
public interface SecurityAdapter {
    int USER_NOT_LOGGED_IN = -1;

    /**
     * Is a user logged in?
     * @return True if logged in, false otherwise
     */
    boolean isLoggedIn();

    /**
     * Get the id of the logged in user
     * @return Id of logged in user, USER_NOT_LOGGED_IN(-1) if not
     */
    int getLoggedInUser();

    /**
     * Log in a given username. Note that it is up to the services layer to coordinate the checking of user
     * information before this method is called.
     * @param userId The user id of the user to log in
     * @return True if login successful, false otherwise
     */
    boolean logIn(int userId);

    /**
     * Log out the currently logged in user
     */
    void logOut();
}
