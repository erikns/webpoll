package no.hib.megagruppe.webpoll.util;

/**
 * Interface encapsulating password hashing and comparison functionality
 */
public interface PasswordHasher {
    /**
     * Takes a password and combines it with a randomly generated salt, then finally
     * encrypts it with a cryptographically secure BCrypt algorithm and encodes it
     * Base64.
     * @param password The password to hash. The parameter cannot be null of an empty
     *                 string.
     * @return The base64-encoded encrypted password
     */
    String hashPassword(String password);

    /**
     * Compares a given password with a given hash
     * @param password The password to compare. The parameter cannot be null or an
     *                 empty string.
     * @param hash The hash to compare against.
     * @return True on valid match, false otherwise
     */
    boolean comparePassword(String password, String hash);
}
