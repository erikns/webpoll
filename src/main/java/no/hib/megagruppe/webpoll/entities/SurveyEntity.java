package no.hib.megagruppe.webpoll.entities;

import java.util.Date;
import java.util.List;

public class SurveyEntity {
    private Integer id;
    private String name;
    private Date date;
    private Date deadline;
    private UserEntity owner;
    private String code;
    private List<QuestionEntity> questions;
    private List<ResponseEntity> responses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }

    public List<ResponseEntity> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponseEntity> responses) {
        this.responses = responses;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
