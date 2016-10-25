package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.util.PasswordHasher;
import no.hib.megagruppe.webpoll.util.SecurePasswordHasher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private Map<Integer, UserEntity> users;

    public InMemoryUserRepository() {
        users = new HashMap<>();

        PasswordHasher hasher = new SecurePasswordHasher();
        String hashedPwd = hasher.hashPassword("test");

        UserEntity user = new UserEntity();
        user.setId(1);
        user.setEmail("admin@stud.hib.no");
        user.setPassword(hashedPwd);
        user.setFirstName("Test");
        user.setLastName("Testesen");

        users.put(user.getId(), user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        for (UserEntity user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public UserEntity add(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity findById(int id) {
        return users.get(id);
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
}
