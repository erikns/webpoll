package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.UserEntity;

public interface UserRepository extends Repository<UserEntity> {
    UserEntity findByEmail(String email);
}
