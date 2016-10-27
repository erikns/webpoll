package no.hib.megagruppe.webpoll.entities;

import java.util.List;

public class QuestionEntity {
    public enum QuestionType {
        MULTIPLE_CHOICE_CHECKBOX, MULTIPLE_CHOICE_RADIO, FREE_TEXT; // XXX OK?
    	
        public boolean isMultipleChoice(){
        	return (this == MULTIPLE_CHOICE_CHECKBOX || this == MULTIPLE_CHOICE_RADIO);
        }
        
        public boolean canHaveMultipleAnswers(){
        	return this == MULTIPLE_CHOICE_CHECKBOX;
        }
    }

    private Integer id;
    private SurveyEntity survey;
    private String text;
    private QuestionType type;
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

    public List<OptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<OptionEntity> options) {
        this.options = options;
    }
}
