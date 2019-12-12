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
    private Controller() {
    }
    //
    public static void setupController(Game game, Gui gui) {
        instance.game = game;
        instance.gui = gui;
    }

    ///////////////////////////////////////////////////////////
    public void startTheGame(String playerName) {
        game.newGame(playerName);
        setQuestion();
        checkTimerSeconds();
    }

    /* setAnswer() will be called when the player will select an answer for the question.
     It's make some changes that fits the situation for example: making the background color of the selection button yellow
     and disable the other selections. It will also temporarily disabling the lifelines. */
    public void setAnswer(char buttonSymbol) { // TODO PlayContent instead of QuestionArea and add delay to button effects-+

        gui.setOptionButtonState(buttonSymbol, OptionButton.CHECKING);
        gui.disableActions();
        Timer.delay(() -> {
            if (game.checkAnswer(buttonSymbol)) {
                gui.setOptionButtonState(buttonSymbol, OptionButton.CORRECT);
                Timer.delay(()->{
                    game.nextQuestion(gui.getShownSeconds());
                    if (Byte.parseByte(game.getValue(Game.CURRENT_QUESTION)) == FINAL_GLOBAL_VARIABLES.getPRIZES().length){
                        endTheGame(false);
                    }else{
                        setQuestion();
                        gui.enableActions();
                        gui.setOptionButtonState(buttonSymbol, OptionButton.DEFAULT);}
                }, 3);


            } else {
                gui.setOptionButtonState(buttonSymbol, OptionButton.WRONG);
                endTheGame(false);
            }
            gui.setLifeLineHint("");
        },3);

    }

    // setQuestion() method brings the question and its options and send them to View (gui) that will show it on the screen.
    private void setQuestion() {
        String question = game.getValue(Game.QUESTION_TEXT);
        String option1 = game.getValue(Game.OPTION1);
        String option2 = game.getValue(Game.OPTION2);
        String option3 = game.getValue(Game.OPTION3);
        String option4 = game.getValue(Game.OPTION4);
        byte currentQuestion = Byte.parseByte(game.getValue(Game.CURRENT_QUESTION));
        gui.updateQuestion(question, option1, option2, option3, option4, currentQuestion);
    }
    // Overriding
    private void setQuestion(String[] fullQuestion) {
        String question = fullQuestion[0];
        String option1 = fullQuestion[1];
        String option2 = fullQuestion[2];
        String option3 = fullQuestion[3];
        String option4 = fullQuestion[4];
        byte currentQuestion = Byte.parseByte(game.getValue(Game.CURRENT_QUESTION));
        gui.updateQuestion(question, option1, option2, option3, option4, currentQuestion);

    }
    //
    public void endTheGame(boolean withDraw){
        gui.setOptionButtonState(game.getCorrectAnswerSymbol() , OptionButton.CORRECT);
        Timer.delay(()->gui.playSound(SoundEffect.WRONG_ANSWER), 0.1);
        Timer.delay(()->{
            String[] moneyCheckData = game.getMoneyCheckData(withDraw);
            gui.showEndGameScreen(moneyCheckData[0],moneyCheckData[1],moneyCheckData[2]);
        }, 3);
    }
    //
    private void checkTimerSeconds(){
        Timer.delay(()->{
            if (gui.getShownSeconds()<=0){
                endTheGame(false);
            }else {checkTimerSeconds();}
        },1);

    }
    // A method to recieve which lifeline is clicked and call the different lifelines.
    public void useLifeLine(String lifeLineSelection) {
        gui.stopTimer();
        Timer.delay(()->{
        switch (lifeLineSelection) {
            case "askThePeople":
                gui.setLifeLineHint(game.askAudience());
                break;
            case "callAFriend":
                gui.setLifeLineHint(game.callAFriend());
                break;
            case "removeHalf":
                setQuestion(game.removeHalf());
                break;
            case "changeQuestion":
                setQuestion(game.changeQuestion());
                break;
            default:
                System.err.println("There is no lifeline related to ("+lifeLineSelection+").");

        }
        },1);
    }
}