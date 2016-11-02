package no.hib.megagruppe.webpoll.testutil;

import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.models.answering.SurveyAnsweringModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SurveyAnsweringModelBuilder {
    public static SurveyAnsweringModel build() {
        UserEntity owner = buildUser();
        QuestionEntity q1 = buildCompleteQuestion();
        QuestionEntity q2 = buildQuestion(2, QuestionEntity.QuestionType.FREE_TEXT, "Spørsmål 2?");

        List<QuestionEntity> questionEntities = new ArrayList<>();
        questionEntities.add(q1);
        questionEntities.add(q2);

        SurveyEntity survey = buildSurvey(owner, questionEntities);

        return new SurveyAnsweringModel(questionEntities, survey.getCode(), survey.getName(),
                survey.getDateCreated(), survey.getDeadline(),
                survey.getOwner().getFirstName() + survey.getOwner().getLastName());
    }

    private static SurveyEntity buildSurvey(UserEntity owner, List<QuestionEntity> questionEntities) {
        SurveyEntity survey = new SurveyEntity();
        survey.setName("Testundersøkelse");
        survey.setCode("testabc");
        survey.setId(1);
        survey.setDateCreated(new Timestamp(System.currentTimeMillis()));
        survey.setDeadline(new Timestamp(System.currentTimeMillis() + 36000));
        survey.setQuestions(questionEntities);
        survey.setOwner(owner);

        for (QuestionEntity q : questionEntities) {
            q.setSurvey(survey);
        }

        return survey;
    }

    private static QuestionEntity buildCompleteQuestion() {
        QuestionEntity q1 = buildQuestion(1, QuestionEntity.QuestionType.MULTIPLE_CHOICE_RADIO, "Spørsmål 1?");
        OptionEntity option1 = buildOptionForQuestion(q1, "A");
        OptionEntity option2 = buildOptionForQuestion(q1, "B");

        List<OptionEntity> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        q1.setOptions(options);
        return q1;
    }

    private static OptionEntity buildOptionForQuestion(QuestionEntity q1, String a) {
        OptionEntity option1 = new OptionEntity();
        option1.setQuestion(q1);
        option1.setText(a);
        return option1;
    }

    private static QuestionEntity buildQuestion(int id, QuestionEntity.QuestionType multipleChoiceRadio, String text) {
        QuestionEntity q1 = new QuestionEntity();
        q1.setId(id);
        q1.setType(multipleChoiceRadio);
        q1.setText(text);
        return q1;
    }

    private static UserEntity buildUser() {
        UserEntity owner = new UserEntity();
        owner.setId(1);
        owner.setFirstName("Test");
        owner.setLastName("Testesen");
        return owner;
    }
}
