package no.hib.megagruppe.webpoll.models;

public class SurveyQuestionModel {
    private String[] options;
    private String text;
    private String answer[];
    private QuestionType questionType; //HVOR og hvordan skal denne settes? 
    								   //XXX Hei! Instansene av denne klassen opprettes i SurveyAnsweringModel, som har all nødvendig informasjon!

    private int counter; //er dette greit til bruk i submitAnswer?


    /**
     * Method to submit a single answer to an Open- or Mulitple-ChoiceQuestion
     * Used if multiple answers are not allowed
     * @param s
     */
    public void submitAnswer(String s){
        //FIXME
    }

    /**
     * Method to submit more than one answer if the question allows it
     * @param s an array of Strings, each representing an answer
     */
    public void submitAnswer(String[] s){
        //FIXME
    }

    /**
     * @return String from enum, representing question type
     */
    public String getType(){
        return questionType.name();
    }
    
    public void setType(QuestionType questionType){
    	this.questionType = questionType;
    }

    /**
     * Method that returns the options from a question, if question 'text'
     * this method will return an empty array
     * @return an array of strings for each option in the question
     */
    public String[] getOptions(){
        return options;
    }


}
