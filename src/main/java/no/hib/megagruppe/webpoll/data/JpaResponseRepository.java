package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.ResponseEntity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequestScoped
public class JpaResponseRepository implements ResponseRepository {
    @PersistenceContext(name = "webpollDb")
    private EntityManager entityManager;

    @Override
    public ResponseEntity add(ResponseEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public ResponseEntity findById(int id) {
        return entityManager.find(ResponseEntity.class, id);
    }

    @Override
    public List<ResponseEntity> findAll() {
        return null;
    }

    @Override
    public ResponseEntity update(ResponseEntity entity) {
        return null;
    }

    @Override
    public void remove(ResponseEntity entity) {

    }
}
