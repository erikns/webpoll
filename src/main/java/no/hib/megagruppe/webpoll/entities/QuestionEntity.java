package no.hib.megagruppe.webpoll.entities;

import java.util.List;

public class QuestionEntity {
    public enum QuestionType {
        MULTIPLE_CHOICE, FREE_TEXT
    }

    private Integer id;
    private SurveyEntity survey;
    private String text;
    private QuestionType type;
    private Boolean multiple;
    private List<OptionEntity> options;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SurveyEntity getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyEntity survey) {
        this.survey = survey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public List<OptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<OptionEntity> options) {
        this.options = options;
    }
}
