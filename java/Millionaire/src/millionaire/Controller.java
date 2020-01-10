package millionaire;

import millionaire.Model.Game;
import millionaire.View.*;
import millionaire.FINAL_GLOBAL_VARIABLES.*;

/**
 * It represents the connection between Gui class and Game class. (The controller of  MVC design pattern).
 *
 * @author Mohammad, Henrik, Jesse, Joakim.
 */

public class Controller {
    //           >>>>Class variables and methods.<<<<
    // The following variable and method created to apply singleton design pattern.
    /**
     * It is the only instance of this class (singleton).
     */
    final private static Controller instance = new Controller();

    /**
     * It returns a controller object.
     * @return the only possible instance of this class (singleton).
     */
    public static Controller getInstance() {
        return instance;
    }
    /////////////////////////////////////////////////////////////

    /**
     *
     * @param game reference to controller
     * @param gui reference to controller
     */
    static void setupController(Game game, Gui gui) {
        instance.game = game;
        instance.gui = gui;
    }
    /////////////////////////////////////////////////////////////
    //              >>>>Member variables.<<<<
    /**
     * game variable represents the View of MVC design pattern.
     */
    private Gui gui;
    /**
     * It represents the Model of MVC design pattern.
     */
    private Game game;

    ////////////////////////////////////////////////////////////
    //              >>>>Class constructor.<<<<
    private Controller() {
    }
    /////////////////////////////////////////////////////////////
    //              >>>>Member variables.<<<<

    /**
     *  start new game for user and name, questions and seconds on timer will start
     * @param playerName create a field that player can insert ID
     */
    public void startTheGame(String playerName) {
        game.newGame(playerName);
        setQuestion();
        checkTimerSeconds();
    }

    /**
     * setAnswer() will be called when the player will select an answer for the question.
     *      It's make some changes that fits the situation for example: making the background color of the selection button yellow
     *      and disable the other selections. It will also temporarily disabling the lifelines.
     * @param buttonSymbol will be yellow if clicked on. green if correct answer. red if wrong answer.
     */

    public void setAnswer(char buttonSymbol) {

        gui.setOptionButtonState(buttonSymbol, OptionButtonState.CHECKING);
        gui.disableActions();
        Timer.delay(() -> {
            if (game.checkAnswer(buttonSymbol)) {
                gui.setOptionButtonState(buttonSymbol, OptionButtonState.CORRECT);
                Timer.delay(()->{
                    game.nextQuestion(gui.getShownSeconds());
                    if (Byte.parseByte(game.getQuestionPart(QuestionPart.CURRENT_QUESTION)) == FINAL_GLOBAL_VARIABLES.getPRIZES().length){
                        endTheGame(false);
                    }else{
                        setQuestion();
                        gui.setOptionButtonState(buttonSymbol, OptionButtonState.DEFAULT);}
                }, 3);


            } else {
                gui.setOptionButtonState(buttonSymbol, OptionButtonState.WRONG);

                endTheGame(false);
            }
            gui.setLifeLineHint("",Byte.parseByte(game.getQuestionPart(QuestionPart.CURRENT_QUESTION)));
        },3);

    }

    /**
     * this method brings the question and its options and send them to View (gui) that will show it on the screen.
     */


    private void setQuestion() {
        String question = game.getQuestionPart(QuestionPart.QUESTION_TEXT);
        String option1 = game.getQuestionPart(QuestionPart.OPTION1);
        String option2 = game.getQuestionPart(QuestionPart.OPTION2);
        String option3 = game.getQuestionPart(QuestionPart.OPTION3);
        String option4 = game.getQuestionPart(QuestionPart.OPTION4);
        byte currentQuestion = Byte.parseByte(game.getQuestionPart(QuestionPart.CURRENT_QUESTION));
        gui.updateQuestion(question, option1, option2, option3, option4, currentQuestion);
    }
    // Overriding
    private void setQuestion(String[] fullQuestion) {
        String question = fullQuestion[0];
        String option1 = fullQuestion[1];
        String option2 = fullQuestion[2];
        String option3 = fullQuestion[3];
        String option4 = fullQuestion[4];
        byte currentQuestion = Byte.parseByte(game.getQuestionPart(QuestionPart.CURRENT_QUESTION));
        gui.updateQuestion(question, option1, option2, option3, option4, currentQuestion);
    }
    //Jesse

    /**
     * if the player is not at the last level of the game and get wrong answer, it will send the correct answer and send wrong sound effect to gui to later show in EndGameScreen.
     *  This method will send the player's name,balance and date to the EndGameScreen.
     * @param withDraw a boolean parameter
     */
    public void endTheGame(boolean withDraw){
        if (Byte.parseByte(game.getQuestionPart(QuestionPart.CURRENT_QUESTION)) != FINAL_GLOBAL_VARIABLES.getPRIZES().length){
            gui.setOptionButtonState(game.getCorrectAnswerSymbol(), OptionButtonState.CORRECT);
            Timer.delay(()->gui.playSound(SoundEffectName.WRONG_ANSWER), 0.1);
        }
        Timer.delay(()->{
            String[] moneyCheckData = game.getMoneyCheckData(withDraw);
            gui.showEndGameScreen(moneyCheckData[0],moneyCheckData[1],moneyCheckData[2]);
        }, 3);
    }

    /**
     * Method that checks if the time reaches 0, and calls the endTheGame method, which ends the game.
     */
    private void checkTimerSeconds(){
        Timer.delay(()->{
            if (gui.getShownSeconds()<=0){
                endTheGame(false);
            }else {checkTimerSeconds();}
        },1);

    }

    /**
     * Calls the different lifeline methods in game class and send the result to the lifelinearea or the questionarea.
     * @param lifeLineSelection The different lifelines
     */
    public void useLifeLine(String lifeLineSelection) {
        gui.stopTimer();
        Timer.delay(()->{
            if(lifeLineSelection.equals(LifeLineType.ASK_AUDIENCE) || lifeLineSelection.equals(LifeLineType.CALL_A_FRIEND)){
                gui.setLifeLineHint(game.runLifeLine(lifeLineSelection)[0],Byte.parseByte(game.getQuestionPart(QuestionPart.CURRENT_QUESTION)));
            }else if (lifeLineSelection.equals(LifeLineType.REMOVE_HALF) || lifeLineSelection.equals(LifeLineType.CHANGE_QUESTION)){
                setQuestion(game.runLifeLine(lifeLineSelection));
            }else {
                System.err.println("There is no lifeline related to ("+lifeLineSelection+").");
            }
        },1);
    }
}