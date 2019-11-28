package millionaire;

import millionaire.Model.Game;
import millionaire.View.*;

// Controller class (MVC design pattern).

public class Controller {

    final private static Controller instance = new Controller();
    public static Controller getInstance() {
        return instance;
    }

///////////////////////////////////////////////////////////////////////
// Controller member variables.
// game variable represents the Model of MVC design pattern.
// gui variable represents the View of MVC design pattern.
    private Gui gui;
    private Game game;

///////////////////////////////////////////////////////////
// Controller constructor.
    private Controller(){ }

///////////////////////////////////////////////////////////
    public void startTheGame() {
        game.setQuestions();
        setQuestion();                                                                                  //TODO checking if the game is full loaded before show game gui
    }

// setAnswer() will be called when the player will select an answer for the question.
// It's make some changes that fits the situation for example: making the background color of the selection button yellow and disable the other selections.
    public void setAnswer(char buttonSymbol) { // TODO PlayContent instead of QuestionArea and add delay to button effects-+
        gui.setOptionButtonState(buttonSymbol, OptionButton.Checking);
        gui.disableActions();
        if (game.checkAnswer(buttonSymbol)) {
            gui.setOptionButtonState(buttonSymbol, OptionButton.Right);
            game.nextQuestion();
            setQuestion();
            gui.enableActions();
            gui.setOptionButtonState(buttonSymbol, OptionButton.Default);
        } else {
            gui.setOptionButtonState(buttonSymbol, OptionButton.Wrong);
        }
        gui.setLifeLineHint("");
    }

// setQuestion() method brings the question and its options and send them to View (gui) that will show it on the screen.
    private void setQuestion(){
        String question = game.getValue(Game.QUESTION_TEXT);
        String option1 = game.getValue(Game.OPTION1);
        String option2 = game.getValue(Game.OPTION2);
        String option3 = game.getValue(Game.OPTION3);
        String option4 = game.getValue(Game.OPTION4);
        String currentQuestion = game.getValue(Game.CURRENT_QUESTION);
        gui.updateQuestion(question, option1, option2, option3, option4, currentQuestion);
    }

//
    public static void setupController (Game game,Gui gui){
        instance.game = game;
        instance.gui = gui;
    }

//Henriks code

// A method to recieve which lifeline is clicked and call the different lifelines.
    public void callTheHintMethods(String lifeLineSelection){
        switch (lifeLineSelection){
            case "askThePeople":
                break;
            case "callAFriend":
                gui.setLifeLineHint(game.callAFriend());
                break;
            case "removeHalf":
                break;
            case "changeQuestion":
                break;
            
        }
    }

}