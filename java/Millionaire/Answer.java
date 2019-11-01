// this class contain all the answer
public class Answer {
    private String text;
    private AnswerToken token;

    public Answer(){}
    // initiate text and a right answer.
    public Answer(AnswerToken tkn, String txt){
        token = tkn;
        text = txt;
    }
    // getter and setter
    public AnswerToken getToken() {
        return token;
    }
    public void setToken(AnswerToken token) {
        this.token = token;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }


}
