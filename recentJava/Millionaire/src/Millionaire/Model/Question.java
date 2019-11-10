package Millionaire.Model;

public class Question{
/////////////////////////////////////////////
//The member variables.
    private String questionText;                                                                                        // The show question to the player.
    private String[] options;                                                                                           // Option variables is the alternative that will be shown to the user.
    private int token;                                                                                                  // Token that refer to the right answer index of the options array.
/////////////////////////////////////////////
// Question constructor
    protected Question(String questionText,String[] options,int token){
        this.questionText = questionText;
        this.options = options;
        this.token= token;

    }
/////////////////////////////////////////////
//The member methods.
//
private static int decodeToken(int encodedToken){
    return (encodedToken-2019)%5;
    }
//
protected String getQuestionText() {
        return questionText;
    }
//
protected String getOption(int index) {
        if(index>0&&index<5)
        return options[index-1];
        else{
            System.out.println("There is something went wrong. \n!! Out of index!!");                                   //error msg
            return "";
        }
    }

// För test skull
public void print(){
    System.out.println(questionText);
    for (int i=0;i<options.length;i++)
        System.out.println(options[i]);
    System.out.println(token);
    }
//
    public boolean checkAnswer(String playerAnswer){
        if(playerAnswer.equals(decodeToken(token)))
        return true;
        else
            return false;
    }
}