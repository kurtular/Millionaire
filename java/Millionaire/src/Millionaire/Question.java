import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {
    private String text;
    private AnswerToken correctAnswer;
    private List<Answer> answers;
    public Question(){}
    // initiate question, right answer and 3 other answer
    public Question(String txt, AnswerToken rightAnswer, Answer ... possibleAnswers){
        this.text = txt;
        this.correctAnswer = rightAnswer;
        this.answers = new ArrayList<>();
        this.answers.addAll(Arrays.asList(possibleAnswers));
    }
    public String getText(String text){
        return  text;
    }
    public void removeAnswer(int index){
        answers.remove(index);
    }

    public void removeAnswer(Answer ans){
        answers.remove(ans);
    }

    public List<Answer> getAnswers() {
        return new ArrayList<>(answers);
    }

    public void setAnswers(List<Answer> choices) {
        this.answers = choices;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AnswerToken getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(AnswerToken answer) {
        this.correctAnswer = answer;
    }

    // correct answer
    public boolean isCorrectAnswer(AnswerToken ans){
        return true;
    }

    public List<Answer> getIncorrectAnswers(){
        List<Answer> incorrectAnswers = new ArrayList<>();
        for(Answer ans: answers){
            if(!isCorrectAnswer(ans.getToken()))
                incorrectAnswers.add(ans);
        }
        return incorrectAnswers;
    }
}
