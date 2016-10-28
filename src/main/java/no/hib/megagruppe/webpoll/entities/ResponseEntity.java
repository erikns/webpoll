package no.hib.megagruppe.webpoll.entities;

import java.util.List;

public class ResponseEntity {
    private Integer id;
    private SurveyEntity survey;
    private List<AnswerEntity> answers;
    
    public ResponseEntity() { 	
    }
    
    public ResponseEntity(SurveyEntity survey, List<AnswerEntity> answers) {
    	this.survey = survey;
    	this.answers = answers;
    }

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

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }
}
