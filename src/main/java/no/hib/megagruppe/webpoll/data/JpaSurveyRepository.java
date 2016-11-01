package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.SurveyEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class JpaSurveyRepository implements SurveyRepository {
    @PersistenceContext(name = "webpollDb")
    private EntityManager entityManager;

    @Override
    public SurveyEntity findByCode(String code) {
        Query query = entityManager.createQuery("select s from survey s where s.code = '"
                + code + "'");
        try {
            return (SurveyEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public SurveyEntity add(SurveyEntity entity) {
        return null;
    }

    @Override
    public SurveyEntity findById(int id) {
        return null;
    }

    @Override
    public List<SurveyEntity> findAll() {
        return null;
    }

    @Override
    public SurveyEntity update(SurveyEntity entity) {
        return null;
    }

    @Override
    public void remove(SurveyEntity entity) {

    }
}
