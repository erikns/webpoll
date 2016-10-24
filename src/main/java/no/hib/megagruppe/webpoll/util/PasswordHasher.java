package no.hib.megagruppe.webpoll.util;

public interface PasswordHasher {
    String hashPassword(String password);
    boolean comparePassword(String password, String hash);
}
