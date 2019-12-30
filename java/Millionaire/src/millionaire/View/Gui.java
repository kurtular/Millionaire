package millionaire.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import millionaire.FINAL_GLOBAL_VARIABLES;

/**
 * The main class of the View package and it's represent the View part of the MVC design pattern.
 *
 * @author Mohammad, Joakim, Henrik, Jesse.
 */
public class Gui extends Application {
    //           >>>>Class variables and methods.<<<<
    /**
     * It created to be able to change gui content from other view classes.
     */
    static Pane content;

    /**
     * It resets application qui (view classes) to be able start a new game without close the application.
     */
    static void reset() {
        PriceTable.getInstance().resetPriceTable();
        LifeLineArea.getInstance().resetLifeLineArea();
        QuestionArea.getInstance().resetQuestionArea();
    }
    /////////////////////////////////////////////////////////////
    //                >>>> Member methods.<<<<

    /**
     * It runs after calling launch(args) that is inside launchGui().
     *
     * @param stage is javafx Stage object.
     */
    @Override
    public void start(Stage stage) {
        content = new Pane();
// set the game intro (IntroScreen) and add it to .
        IntroScreen.getInstance().show();
        Scene scene = new Scene(content);
        stage.setScene(scene);
// set some properties values for the stage object like a title ,frame icon and game frame dimensions(min).
        stage.setTitle("Miljonär");
        scene.getStylesheets().add("style.css");
        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/logo.png"));
        stage.show();
    }

    /**
     * It call launch javafx method (to launch the gui of the game.).
     * <br>It created to be able to separate javafx application from main method.
     *
     * @param args is mains String array (It is required to call Launch method (javafx))
     */
    public void launchGui(String[] args) {
        launch(args);
    }

    /**
     * It passes the question that will be shown to the PlayContent class (window will be shown during the match). see {@link QuestionArea#updateQuestion(String, String, String, String, String, String, byte)}
     * <br>It updates PriceTable also (the table that show question order and its prize).
     * <br>It reset the timer and enable the possible actions like select an answer or the life lines button and withdraw button.
     *
     * @param question        is a question text.
     * @param option1         is the first possible alternative.
     * @param option2         is the second possible alternative.
     * @param option3         is the third possible alternative.
     * @param option4         is the fourth possible alternative.
     * @param currentQuestion is a number that refer to question order.
     */
    public void updateQuestion(String question, String option1, String option2, String option3, String option4, byte currentQuestion) {
        // Update PriceTable.
        PriceTable.getInstance().setCurrentPlace(currentQuestion);
        // Show the question with its options and show players current balance.
        String balance = FINAL_GLOBAL_VARIABLES.getPRIZES()[currentQuestion - 1];
        QuestionArea.getInstance().updateQuestion(question, option1, option2, option3, option4, balance, currentQuestion);
        // Reset the timer.
        TimerLabel.getInstance().resetTimer();
        TimerLabel.getInstance().startTimer();
        // Enable all possible actions.
        enableActions();
    }

    /**
     * It set Option button state. see {@link QuestionArea#setOptionButtonState(char, int)}
     *
     * @param optionButtonSymbol refers to an option button(A,B,C,D).
     * @param OptionButtonState  refers to a state that an option button will have.
     */
    public void setOptionButtonState(char optionButtonSymbol, int OptionButtonState) {
        QuestionArea.getInstance().setOptionButtonState(optionButtonSymbol, OptionButtonState);
    }

    /**
     * Enable the possible actions in different view classes.
     */
    private void enableActions() {
        // Enable withdraw button.
        PlayScreen.getInstance().enableWithdrawing();
        // Allow the player to select an answer.
        QuestionArea.getInstance().enableActions();
        // Enable life lines.
        LifeLineArea.getInstance().activate();
    }

    /**
     * Disable the possible actions in different view classes.
     */
    public void disableActions() {
        // Disable withdraw button.
        PlayScreen.getInstance().disableWithdrawing();
        // Prevent the player to select an answer.
        QuestionArea.getInstance().disableActions();
        // Disable life lines.
        LifeLineArea.getInstance().deactivateTemporarily();
    }

    /**
     * Push the hint forward, reset timer, enable actions and play a sound.
     *
     * @param hint            a text that will be shown as a life line hint.
     * @param currentQuestion is a number that refer to question order.
     */
    public void setLifeLineHint(String hint, byte currentQuestion) {
        // Show the hint.
        LifeLineArea.getInstance().setLifeLineHint(hint);
        // Reset the timer when hint value is empty.
        if (!hint.isEmpty()) {
            TimerLabel.getInstance().resetTimer();
            TimerLabel.getInstance().startTimer();
            enableActions();
            QuestionArea.getInstance().playSound(currentQuestion);
        }
    }

    /**
     * It shows EndGameScreen (the screen that will show money check with player information (name, balance,game date))
     *
     * @param playerName    refers to player name that was entered of the player at game start.
     * @param playerBalance refers to the balance (prize) that player got.
     * @param gameDate      refers to game date.
     */
    public void showEndGameScreen(String playerName, String playerBalance, String gameDate) {
        EndGameScreen.show(content, playerName, playerBalance, gameDate);
    }

    /**
     * It stops game timer. see {@link TimerLabel#stopTimer()}
     */
    public void stopTimer() {
        TimerLabel.getInstance().stopTimer();
    }

    /**
     * It gets the shown seconds in TimerLabel.
     *
     * @return a number that refer to remaining seconds from TimerLabel.
     */
    public byte getShownSeconds() {
        return TimerLabel.getInstance().getShownSeconds();
    }

    /**
     * It plays a sound truck depending on EffectName value.
     *
     * @param SoundEffectName refers to a sound truck will be played. see {@link FINAL_GLOBAL_VARIABLES.SoundEffectName}
     */
    public void playSound(String SoundEffectName) {
        SoundEffectPlayer.play(SoundEffectName);
    }
}
