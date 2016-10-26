package no.hib.megagruppe.webpoll.models;

import java.util.Date;

public class AnswerModel {
    private QuestionModel[] questions;
    private int counter;

    public String SurveyTitle;
    public Date DateCreated;
    public Date DateDeadline;
    public String Creator;

    /**
     * Gives the next question in the questions[]
     * @return a QuestionModel representation of the question or null if there are no more questions
     */
    public QuestionModel getNextQuestion(){
        QuestionModel ret = null;
        if (hasNextQuestion()){
            counter++;
            ret = questions[counter -1];
        }
        return ret;
    }


    //Jeg er usikker pa hva vi mente med denne
    public String getTimeDencining(){
        return null;
    }

    /**
     * Checks if there are more questions in the questions array
     * This method should only be used when iterating questions[]
     * @return true if the end of questions[] is not reached
     */
    public boolean hasNextQuestion() {
        boolean retur;

        retur = questions != null && counter < questions.length;
        return retur;
    }

    /**
     * HelperMethod, resets counter used to iterate :questions in Answermodel
     */
    private void reset(){
        this.counter = 0;
    }

    /**
     * Method that returns counter
     * @return current counter
     */
    public int getCounter() {
        return counter;
    }
}
