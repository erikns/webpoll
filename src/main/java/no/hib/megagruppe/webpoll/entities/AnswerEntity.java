package no.hib.megagruppe.webpoll.entities;

public class AnswerEntity {
    private Integer id;
    private ResponseEntity response;
    private QuestionEntity question;
    private OptionEntity option;
    private String freetext;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
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
