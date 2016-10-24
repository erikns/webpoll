package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// TEMPORARY!!!!!!
public class InMemorySurveyRepository implements SurveyRepository {
    private List<SurveyEntity> surveys;

    public InMemorySurveyRepository() {
        surveys = new ArrayList<>();

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

        surveys.add(entity);
    }

    @Override
    public SurveyEntity add(SurveyEntity entity) {
        surveys.add(entity);
        return entity;
    }

    @Override
    public SurveyEntity findById(int id) {
        SurveyEntity result = null;
        for (SurveyEntity survey : surveys) {
            result = survey;
        }
        return result;
    }

    @Override
    public List<SurveyEntity> findAll() {
        return surveys;
    }

    @Override
    public SurveyEntity update(SurveyEntity entity) {
        SurveyEntity current = findById(entity.getId());
        if (current != null) {
            surveys.remove(current);
            surveys.add(entity);
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public void remove(SurveyEntity entity) {
        surveys.remove(entity);
    }

    @Override
    public SurveyEntity findByCode(String code) {
        SurveyEntity result = null;
        for (SurveyEntity survey : surveys) {
            if (survey.getCode().equals(code)) {
                result = survey;
            }
        }
        return result;
    }
}
