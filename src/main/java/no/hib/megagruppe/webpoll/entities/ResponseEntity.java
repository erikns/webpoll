package no.hib.megagruppe.webpoll.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "response")
@Table(schema = "webpoll", name = "response")
public class ResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private SurveyEntity survey;
    @OneToMany(targetEntity = AnswerEntity.class, cascade = CascadeType.PERSIST, mappedBy = "response",  fetch = FetchType.EAGER)
    private List<AnswerEntity> answers;
    
    public ResponseEntity() {
    }
    
    public ResponseEntity(SurveyEntity survey, List<AnswerEntity> answers) {
    	this.survey = survey;
    	this.answers = answers;

        for (AnswerEntity a : answers) {
            a.setResponse(this);
        }
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
