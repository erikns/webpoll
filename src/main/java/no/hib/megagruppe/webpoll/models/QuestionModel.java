package no.hib.megagruppe.webpoll.models;

public class QuestionModel {
    private String[] options;
    private String text;
    private String answer[];


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
     * @return enum question type
     */
    public Enum getType(){
        //FIXME need to implement this Enum, -OpenQuestion-, -MultipleChoiceQuestion- and add it to this method
        return null;
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
