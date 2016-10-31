package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class JpaUserRepository implements UserRepository {

    @PersistenceContext(name = "webpollDb")
    private EntityManager em;

    @Override
    public UserEntity findByEmail(String email) {
        Query query = em.createQuery("select u from user u where u.email = '" + email + "'");
        Object queryResult = query.getSingleResult();
        return (UserEntity) queryResult;
    }

    @Override
    public UserEntity add(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity findById(int id) {
        return em.find(UserEntity.class, id);
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
