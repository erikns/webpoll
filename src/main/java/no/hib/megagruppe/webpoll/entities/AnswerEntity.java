package no.hib.megagruppe.webpoll.entities;

import javax.persistence.*;

@Entity(name = "answer")
@Table(schema = "webpoll", name = "answer")
public class AnswerEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private QuestionEntity question;
    @OneToOne
    private OptionEntity option;
    private String freetext;
    
    public AnswerEntity() {
    	
    }
    
    public AnswerEntity(QuestionEntity question, OptionEntity option, String freetext) {
    	this.question = question;
    	this.option = option;
    	this.freetext = freetext;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public OptionEntity getOption() {
        return option;
    }

    public void setOption(OptionEntity option) {
        this.option = option;
    }

    public String getFreetext() {
        return freetext;
    }

    public void setFreetext(String freetext) {
        this.freetext = freetext;
    }
}
