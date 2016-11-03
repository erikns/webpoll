package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.ResponseEntity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
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
        Query query = entityManager.createQuery("select r from response r");
        try {
            List resultList = query.getResultList();

            List<ResponseEntity> responseList = new ArrayList<>();
            for (Object o : resultList) {
                responseList.add((ResponseEntity) o);
            }

            return responseList;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public ResponseEntity update(ResponseEntity entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void remove(ResponseEntity entity) {
        entityManager.remove(entity);
    }
}
