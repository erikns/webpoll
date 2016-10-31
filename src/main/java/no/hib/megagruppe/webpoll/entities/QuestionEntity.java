package no.hib.megagruppe.webpoll.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "question")
@Table(schema = "webpoll", name = "question")
public class QuestionEntity {
    public enum QuestionType {
        MULTIPLE_CHOICE_CHECKBOX, MULTIPLE_CHOICE_RADIO, FREE_TEXT;
    	
        public boolean isMultipleChoice(){
        	return (this == MULTIPLE_CHOICE_CHECKBOX || this == MULTIPLE_CHOICE_RADIO);
        }
        
        public boolean canHaveMultipleAnswers(){
        	return this == MULTIPLE_CHOICE_CHECKBOX;
        }
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyEntity survey;
    @Column(name = "question_text")
    private String text;
    @Column(name = "question_type")
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    @OneToMany(targetEntity = OptionEntity.class, cascade = CascadeType.ALL, mappedBy = "question",
        fetch = FetchType.EAGER)
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
