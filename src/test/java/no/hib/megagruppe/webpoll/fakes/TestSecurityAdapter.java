package no.hib.megagruppe.webpoll.fakes;

import no.hib.megagruppe.webpoll.services.SecurityAdapter;

public class TestSecurityAdapter implements SecurityAdapter {
    public boolean isLoggedInToReturn = false;
    public int isLoggedInCalled = 0;
    public boolean logInToReturn = false;
    public int logInCalledWith;
    public int logInCalled = 0;
    public int logOutCalled = 0;
    public int getLoggedInUserToReturn;

    @Override
    public boolean isLoggedIn() {
        isLoggedInCalled++;
        return isLoggedInToReturn;
    }

    @Override
    public int getLoggedInUser() {
        return getLoggedInUserToReturn;
    }

    @Override
    public boolean logIn(int userId) {
        logInCalledWith = userId;
        logInCalled++;
        return logInToReturn;
    }

    @Override
    public void logOut() {
        logOutCalled++;
    }
}
