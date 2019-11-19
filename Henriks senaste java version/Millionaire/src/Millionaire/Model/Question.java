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
// decodeToken() will decode the question token to get the index of the right answer. returned indexes will be between (1-4) ((it will not return  0))
private static int decodeToken(int encodedToken){
    return (encodedToken-2019)%5;
    }

// getValue() return a specific string value of the question object depending on value parameter.
protected String getValue(byte value) {
        String returnedValue;
        switch (value){
            case 0:returnedValue = questionText;break;
            case 1:returnedValue = options[0];break;
            case 2:returnedValue = options[1];break;
            case 3:returnedValue = options[2];break;
            case 4:returnedValue = options[3];break;
            default:returnedValue =""; break;
        }
        return returnedValue;
    }

// checkAnswer() will return either true or false depending on the playerAnswerIndex value. ()
    public boolean checkAnswer(byte playerAnswerIndex){
        if(playerAnswerIndex == decodeToken(token))
        return true;
        else
            return false;
    }
}