package no.hib.megagruppe.webpoll.fakes;

import no.hib.megagruppe.webpoll.data.UserRepository;
import no.hib.megagruppe.webpoll.entities.UserEntity;

import java.util.List;

public class TestUserRepository implements UserRepository {
    public UserEntity userToReturn;
    public int findByEmailCalled;
    public String findByEmailCalledWith;
    public int findByIdCalledWith;
    public int findByIdCalled;

    @Override
    public UserEntity add(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity findById(int id) {
        findByIdCalled++;
        findByIdCalledWith = id;
        return userToReturn;
    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public UserEntity update(UserEntity entity) {
        return null;
    }

    @Override
    public void remove(UserEntity entity) {

    }

    @Override
    public UserEntity findByEmail(String email) {
        findByEmailCalled++;
        findByEmailCalledWith = email;
        return userToReturn;
    }
}
