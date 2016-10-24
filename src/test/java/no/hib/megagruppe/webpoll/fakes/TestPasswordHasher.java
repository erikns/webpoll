package no.hib.megagruppe.webpoll.fakes;

import no.hib.megagruppe.webpoll.util.PasswordHasher;

/**
 * Dummy password hasher that just does nothing to the password
 */
public class TestPasswordHasher implements PasswordHasher {
    public boolean hashPasswordCalled = false;
    public boolean comparePasswordCalled = false;

    @Override
    public String hashPassword(String password) {
        hashPasswordCalled = true;
        return password;
    }

    @Override
    public boolean comparePassword(String password, String hash) {
        comparePasswordCalled = true;
        return password.equals(hash);
    }
}
