package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
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

        //////
        OptionEntity optionA = new OptionEntity();
        optionA.setId(1);
        optionA.setText("Ja");

        OptionEntity optionB = new OptionEntity();
        optionB.setId(2);
        optionB.setText("Nei");

        QuestionEntity question1 = new QuestionEntity();
        question1.setId(1);
        question1.setText("Har du noen gang programmert JavaEE?");
        question1.setMultiple(false);
        question1.setType(QuestionEntity.QuestionType.MULTIPLE_CHOICE);

        List<OptionEntity> options = new ArrayList<>();
        options.add(optionA);
        options.add(optionB);
        question1.setOptions(options);

        optionA.setQuestion(question1);
        optionB.setQuestion(question1);
        //////
        //////
        QuestionEntity question2 = new QuestionEntity();
        question2.setId(2);
        question2.setText("Hva synes du om WebPoll?");
        question2.setType(QuestionEntity.QuestionType.FREE_TEXT);
        //////

        SurveyEntity survey = new SurveyEntity();
        survey.setId(1);
        survey.setName("Testundersøkelse");
        survey.setDate(new Date(System.currentTimeMillis() - 3600));
        survey.setDeadline(new Date(System.currentTimeMillis() + 36000));
        survey.setOwner(user);
        survey.setActive(true);
        survey.setCode("testabc");

        List<QuestionEntity> questions = new ArrayList<>();
        question1.setSurvey(survey);
        question2.setSurvey(survey);
        questions.add(question1);
        questions.add(question2);

        survey.setQuestions(questions);

        surveys.put(survey.getId(), survey);
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
