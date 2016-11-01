package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.UserEntity;

/**
 * Repository interface specific to the UserEntity. All methods specific to the UserEntity
 * should be listed here.
 */
public interface UserRepository extends Repository<UserEntity> {
    /**
     * Finds a user based on their email in the system
     * @param email The email to search for
     * @return The found user, or null on error
     */
    UserEntity findByEmail(String email);
}
