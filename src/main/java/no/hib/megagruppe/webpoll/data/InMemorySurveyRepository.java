package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;

import java.util.*;

// TEMPORARY!!!!!!
public class InMemorySurveyRepository implements SurveyRepository {
    private Map<Integer, SurveyEntity> surveys;

    public InMemorySurveyRepository() {
        surveys = new HashMap<>();

        UserEntity user = new UserEntity();
        user.setEmail("test@testesen.no");
        user.setFirstName("Test");
        user.setLastName("Testesen");
        user.setId(1);

        SurveyEntity entity = new SurveyEntity();
        entity.setId(1);
        entity.setName("Testundersøkelse");
        entity.setDate(new Date(System.currentTimeMillis() - 3600));
        entity.setDeadline(new Date(System.currentTimeMillis() + 36000));
        entity.setOwner(user);

        surveys.put(entity.getId(), entity);
    }

    @Override
    public SurveyEntity add(SurveyEntity entity) {
        surveys.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public SurveyEntity findById(int id) {
        SurveyEntity result = null;
        for (SurveyEntity survey : surveys.values()) {
            result = survey;
        }
        return result;
    }

    @Override
    public List<SurveyEntity> findAll() {
        List<SurveyEntity> result = new ArrayList<>();
        for (SurveyEntity e : surveys.values()) {
            result.add(e);
        }
        return result;
    }

    @Override
    public SurveyEntity update(SurveyEntity entity) {
        SurveyEntity current = findById(entity.getId());
        if (current != null) {
            surveys.put(entity.getId(), entity);
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public void remove(SurveyEntity entity) {
        surveys.remove(entity.getId());
    }

    @Override
    public SurveyEntity findByCode(String code) {
        SurveyEntity result = null;
        for (SurveyEntity survey : surveys.values()) {
            if (survey.getCode().equals(code)) {
                result = survey;
            }
        }
        return result;
    }
}
