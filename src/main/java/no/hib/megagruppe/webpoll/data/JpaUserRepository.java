package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("unused")
@RequestScoped
public class JpaUserRepository implements UserRepository {

    @PersistenceContext(name = "webpollDb")
    private EntityManager em;

    @Override
    public UserEntity findByEmail(String email) {
        Query query = em.createQuery("select u from user u where u.email = '" + email + "'");

        try {
            Object queryResult = query.getSingleResult();
            return (UserEntity) queryResult;
        } catch (NoResultException e) {
            return null; // TODO: maybe throw custom exception later
        }
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
