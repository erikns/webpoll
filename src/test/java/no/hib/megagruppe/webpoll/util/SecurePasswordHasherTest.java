package no.hib.megagruppe.webpoll.util;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class SecurePasswordHasherTest {
    @Test(expected = IllegalArgumentException.class)
    public void hashPasswordWithNullPasswordThrowsException() {
        PasswordHasher passwordHasher = new SecurePasswordHasher();
        passwordHasher.hashPassword(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void hashPasswordWithZeroLengthPasswordThrowsException() {
        PasswordHasher passwordHasher = new SecurePasswordHasher();
        passwordHasher.hashPassword("");
    }

    @Test
    public void hashPasswordWithPasswordProducesUniqueSaltedHash() {
        PasswordHasher passwordHasher = new SecurePasswordHasher();

        String result1 = passwordHasher.hashPassword("testpassword");
        String result2 = passwordHasher.hashPassword("testpassword");

        assertTrue(result1.length() > 0);
        assertTrue(result2.length() > 0);

        assertFalse(result1.equals(result2)); // random salt produces different results
    }

    @Test
    public void comparePasswordWithCorrectPasswordReturnsTrue() {
        PasswordHasher passwordHasher = new SecurePasswordHasher();

        String hash = passwordHasher.hashPassword("password");
        assertTrue(passwordHasher.comparePassword("password", hash));
    }

    @Test
    public void comparePasswordWithIncorrectPasswordReturnsFalse() {
        PasswordHasher passwordHasher = new SecurePasswordHasher();

        String hash = passwordHasher.hashPassword("password");
        assertFalse(passwordHasher.comparePassword("passw0rd", hash));
    }

    @Test(expected = IllegalArgumentException.class)
    public void comparePasswordWithNullPasswordThrowsException() {
        PasswordHasher passwordHasher = new SecurePasswordHasher();

        String hash = passwordHasher.hashPassword("password");
        passwordHasher.comparePassword(null, hash);
    }

    @Test(expected = IllegalArgumentException.class)
    public void comparePasswordWithZeroLengthThrowsException() {
        PasswordHasher passwordHasher = new SecurePasswordHasher();

        String hash = passwordHasher.hashPassword("password");
        passwordHasher.comparePassword("", hash);
    }

    // TODO: add some more bad-path tests for password hasher

}
