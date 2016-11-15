package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
@RequestScoped
public class JpaUserRepository implements UserRepository {

    @PersistenceContext(name = "webpollDb")
    private EntityManager em;

    private Logger logger;

    public JpaUserRepository() {
        logger = Logger.getAnonymousLogger();
    }

    @Override
    public UserEntity findByEmail(String email) {
        Query query = em.createQuery("select u from user u where u.email = :email");
        query.setParameter("email", email);

        try {
            Object queryResult = query.getSingleResult();
            return (UserEntity) queryResult;
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "Unable to find user " + email, e);
            return null;
        }
    }

    @Override
    public UserEntity add(UserEntity entity) {
        try {
            em.persist(entity);
            return entity;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public UserEntity findById(int id) {
        return em.find(UserEntity.class, id);
    }

    @Override
    public List<UserEntity> findAll() {
        Query query = em.createQuery("select u from user u");
        try {
            List resultList = query.getResultList();

            List<UserEntity> userList = new ArrayList<>();
            for (Object o : resultList) {
                userList.add((UserEntity) o);
            }

            return userList;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public UserEntity update(UserEntity entity) {
        return em.merge(entity);
    }

    @Override
    public void remove(UserEntity entity) {
        em.remove(entity);
    }
}
