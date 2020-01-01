package millionaire.View;

import millionaire.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import millionaire.FINAL_GLOBAL_VARIABLES;
import millionaire.FINAL_GLOBAL_VARIABLES.*;

/**
 * It represents question area there will be shown a question and its alternatives , timer and player's current balance.
 *
 * @author Mohammad, Joakim.
 */
class QuestionArea extends BorderPane {
    //           >>>>Class variables and methods.<<<<
    // The following variable and method created to apply singleton design pattern.
    /**
     * It is the only instance of this class (singleton).
     */
    final private static QuestionArea instance = new QuestionArea();

    /**
     * It returns a QuestionArea object.
     *
     * @return the only possible instance of this class (singleton)
     */
    static QuestionArea getInstance() {
        return instance;
    }

    /////////////////////////////////////////////////////////////
    //              >>>>Member variables.<<<<
    // Options buttons
    private final OptionButton buttonA;
    private final OptionButton buttonB;
    private final OptionButton buttonC;
    private final OptionButton buttonD;
    // Label that will hold a question.
    private final TextLabel question;
    // Label that will hold a player balance.
    private final TextLabel balance;
    ////////////////////////////////////////////////////////////
    //              >>>>Class constructor.<<<<

    /**
     * It is a constructor method creates a QuestionArea object.
     */
    private QuestionArea() {
        // call BorderPane constructor.
        super();

        // Setup QuestionArea member variables.
        int space = 10;                                                                                                  // Empty space between option buttons.
        this.setPadding(new Insets(0, 0, space, 0));
        question = new TextLabel(790, 99);
        buttonA = new OptionButton();
        buttonB = new OptionButton();
        buttonC = new OptionButton();
        buttonD = new OptionButton();
        balance = new TextLabel(494, 56);
        // Label that will hold the Timer.
        TimerLabel timer = TimerLabel.getInstance();
        timer.setId("timer");

        // Setting Options buttons in the correct place.
        VBox column1 = new VBox(buttonA, buttonC);
        column1.setSpacing(space);
        column1.setPadding(new Insets(space, 0, space, 35));
        VBox column2 = new VBox(buttonB, buttonD);
        column2.setSpacing(space);
        column2.setPadding(new Insets(space, 35, space, 0));
        this.setLeft(column1);
        this.setRight(column2);

        // Setting question text in the correct place.
        this.setTop(question);
        setAlignment(question, Pos.TOP_CENTER);

        // Setting player balance in the correct place.
        this.setBottom(balance);
        setAlignment(balance, Pos.BOTTOM_CENTER);

        // Setting timer in the correct place.
        this.setCenter(timer);
        setMargin(timer, new Insets(-50));

        // Set options buttons actions.
        this.setActions();
    }
    /////////////////////////////////////////////////////////////
    //                >>>> Member methods.<<<<

    /**
     * It sets (define) actions to every option button which question area have(A,B,C,D).
     */
    private void setActions() {
        buttonA.setOnMouseClicked(event -> setAnswer('A'));
        buttonB.setOnMouseClicked(event -> setAnswer('B'));
        buttonC.setOnMouseClicked(event -> setAnswer('C'));
        buttonD.setOnMouseClicked(event -> setAnswer('D'));
    }

    /**
     * It enables option buttons which question area have(A,B,C,D). (player will be able to click them)
     */
    void enableActions() {
        buttonA.setDisable(false);
        buttonB.setDisable(false);
        buttonC.setDisable(false);
        buttonD.setDisable(false);
    }

    /**
     * It disables option buttons which question area have(A,B,C,D). (player will be not able to click them)
     */
    void disableActions() {
        buttonA.setDisable(true);
        buttonB.setDisable(true);
        buttonC.setDisable(true);
        buttonD.setDisable(true);
        buttonA.setOpacity(1);
        buttonB.setOpacity(1);
        buttonC.setOpacity(1);
        buttonD.setOpacity(1);
    }

    /**
     * It changes the style (state) of an option buttons that question area have depending on optionButtonSymbol value.
     *
     * @param optionButtonSymbol refers to an option button that question area have (A,B,C,D).
     * @param OptionButtonState  refers to a state that an option button will have.
     */
    void setOptionButtonState(char optionButtonSymbol, int OptionButtonState) {
        switch (optionButtonSymbol) {
            case 'A':
                buttonA.setState(OptionButtonState);
                break;
            case 'B':
                buttonB.setState(OptionButtonState);
                break;
            case 'C':
                buttonC.setState(OptionButtonState);
                break;
            case 'D':
                buttonD.setState(OptionButtonState);
                break;
            default:
                System.out.println("!!Something went wrong!!\nCheck setOptionButtonState method : View > QuestionArea > setOptionButtonState().");
        }
    }

    /**
     * It update question area (show a question with its alternatives and update player balance.)
     *
     * @param question        is a question text.
     * @param option1         is the first possible alternative.
     * @param option2         is the second possible alternative.
     * @param option3         is the third possible alternative.
     * @param option4         is the fourth possible alternative.
     * @param balance         is the balance the player have at the moment.
     * @param currentQuestion is a number that refer to question order.
     */
    void updateQuestion(String question, String option1, String option2, String option3, String option4, String balance, byte currentQuestion) {
        instance.question.setText(question);
        instance.buttonA.setText(option1);
        instance.buttonB.setText(option2);
        instance.buttonC.setText(option3);
        instance.buttonD.setText(option4);
        instance.balance.setText(FINAL_GLOBAL_VARIABLES.getCurrencySymbol() + balance);
        playSound(currentQuestion);
    }

    /**
     * It sets the player answer (it will be called when the player select an answer on a question).
     *
     * @param answerSymbol refers to selected answer.
     */
    private void setAnswer(char answerSymbol) {
        // Stop the timer.
        TimerLabel.getInstance().stopTimer();
        // Deactivate temporarily life lines.
        LifeLineArea.getInstance().deactivateTemporarily();
        // Send the selected answer to the controller to check if it is the correct answer.
        Controller.getInstance().setAnswer(answerSymbol);
    }

    /**
     * It plays a sound truck depending on currentQuestion value.
     *
     * @param currentQuestion refers to the current question order.
     */
    void playSound(byte currentQuestion) {
        if (currentQuestion < 6) {
            SoundEffectPlayer.play(SoundEffectName.FIRST_5_QUESTIONS);
        } else if (currentQuestion < 11) {
            SoundEffectPlayer.play(SoundEffectName.SECOND_5_QUESTIONS);
        } else if (currentQuestion == 11) {
            SoundEffectPlayer.play(SoundEffectName.ELEVEN);
        } else if (currentQuestion < 14) {
            SoundEffectPlayer.play(SoundEffectName.TWELVE_THIRTEEN);
        } else if (currentQuestion == 14) {
            SoundEffectPlayer.play(SoundEffectName.FOURTEEN);
        } else {
            SoundEffectPlayer.play(SoundEffectName.FIFTEEN);
        }
    }

    /**
     * It resets question area's option buttons to the default state.
     */
    void resetQuestionArea() {
        buttonA.setState(OptionButtonState.DEFAULT);
        buttonB.setState(OptionButtonState.DEFAULT);
        buttonC.setState(OptionButtonState.DEFAULT);
        buttonD.setState(OptionButtonState.DEFAULT);
    }
}
