package no.hib.megagruppe.webpoll.entities;

import javax.persistence.*;

@Entity(name = "answer")
@Table(schema = "webpoll", name = "answer")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private QuestionEntity question;
    @OneToOne
    private OptionEntity option;
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "response_id")
    private ResponseEntity response;
    @Column(name = "free_text")
    private String freetext;
    
    public AnswerEntity() {
    	
    }
    
    public AnswerEntity(QuestionEntity question, String freetext) {
    	this.question = question;
    	this.option = null;
    	this.freetext = freetext;
    }
    
    public AnswerEntity(QuestionEntity question, OptionEntity option) {
    	this.question = question;
    	this.option = option;
    	this.freetext = null;
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

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }
}
