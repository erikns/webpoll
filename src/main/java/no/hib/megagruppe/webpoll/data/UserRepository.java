package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.UserEntity;

/**
 * Repository interface specific to the UserEntity. All methods specific to the UserEntity
 * should be listed here.
 */
public interface UserRepository extends Repository<UserEntity> {
    UserEntity findByEmail(String email);
}
