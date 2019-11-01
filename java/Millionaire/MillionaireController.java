import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// controller model and view
public class MillionaireController {
    private static Player player;
    public static void main(String [] args){

        List<Question> questions = loadQuestions();
        player.displayIntro();
    }
    //
    private static List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();

        // example
        // questions.add(new Question("What does NBA most commonly stand for?", AnswerToken.A,
        return questions;
    }
}
