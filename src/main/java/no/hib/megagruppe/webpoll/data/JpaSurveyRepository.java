package no.hib.megagruppe.webpoll.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import no.hib.megagruppe.webpoll.entities.SurveyEntity;

@SuppressWarnings("unused")
@RequestScoped
public class JpaSurveyRepository implements SurveyRepository {
    @PersistenceContext(name = "webpollDb")
    private EntityManager entityManager;

    @Override
    public SurveyEntity findByCode(String code) {
        Query query = entityManager.createQuery("select s from survey s where s.code = :code");
        query.setParameter("code", code);
        try {
            return (SurveyEntity) query.getSingleResult();
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public SurveyEntity add(SurveyEntity entity) {
        try {
            entityManager.persist(entity);
            return entity;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public SurveyEntity findById(int id) {
        return entityManager.find(SurveyEntity.class, id);
    }

    @Override
    public List<SurveyEntity> findAll() {
        Query query = entityManager.createQuery("select s from survey s");
        try {
            List resultList = query.getResultList();

            List<SurveyEntity> surveyList = new ArrayList<>();
            for (Object o : resultList) {
                surveyList.add((SurveyEntity) o);
            }

            return surveyList;
        } catch (RuntimeException e) {
            return null;
        }
    }
    
    @Override
    public List<SurveyEntity> findAllByUser(Integer userId){
    	Query query = entityManager.createQuery("select s from survey s where s.owner.id = :userId");
        query.setParameter("userId", userId);

        List resultList = query.getResultList();

        List<SurveyEntity> surveyList  = new ArrayList<>();
    	for (Object o : resultList) {
            surveyList.add((SurveyEntity) o);
        }
    	
    	return surveyList;
    }
    
    @Override
    public Long numberOfResponses(SurveyEntity survey){
    	Query query = entityManager.createQuery("select count(r) from response r where r.survey = :survey");
    	query.setParameter("survey", survey);
    	return (Long) query.getSingleResult();
    }
    
    @Override
    public boolean existsActiveSurveyWithCode(String code){
    	Date nowDate = new Date();
    	Timestamp now = new Timestamp(nowDate.getTime());
    	Query query = entityManager.createQuery("select count(s) from survey s where s.code = :code and s.deadline > :now");
        query.setParameter("code", code);
        query.setParameter("now", now);

    	Long count = (Long) query.getSingleResult();
    	return count >= 1;
    }

    @Override
    public SurveyEntity update(SurveyEntity entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void remove(SurveyEntity entity) {
        entityManager.remove(entity);
    }
}
