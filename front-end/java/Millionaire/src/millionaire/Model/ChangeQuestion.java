package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES.*;

/**
 * This class represent Change Question life line and it extends LifeLine class.
 * The only different between the other life lines and this one is isRunning member variable that ChangeQuestion have.
 * Because of there is just one change question life line , Singleton design pattern is used.
 *
 * @author Mohammad.
 */
class ChangeQuestion extends LifeLine {
    //           >>>>Class variables and methods.<<<<
    // The following variable and method created to apply singleton design pattern.
    /**
     * It is the only instance of this class (singleton).
     */
    private static final ChangeQuestion instance = new ChangeQuestion();

    /**
     * It returns change question life line object.
     *
     * @return the only possible instance of this class (singleton).
     */
    static ChangeQuestion getInstance() {
        return instance;
    }
    /////////////////////////////////////////////////////////////
    //              >>>>Member variables.<<<<
    /**
     * It stores the status of the change question life line ( if is running will store true otherwise false).<br>
     * It's needed because the change question lifeline will affect the other life lines when is running (used).
     */
    private boolean isRunning;

    ////////////////////////////////////////////////////////////
    //              >>>>Class constructor.<<<<

    /**
     * It is a constructor method creates a ChangeQuestion object.
     */
    private ChangeQuestion() {
        // call LifeLine class constructor.
        super();
        // setBehavior() method is inherited from LifeLine and uses to set the behavior of change question life line.
        setBehavior(() -> {
            // get the reserve question (it always has the first place of the questions array)
            Question reserveQuestion = LifeLineBehavior.game.questions[0];
            return new String[]{reserveQuestion.getValue(QuestionPart.QUESTION_TEXT), reserveQuestion.getValue(QuestionPart.OPTION1), reserveQuestion.getValue(QuestionPart.OPTION2), reserveQuestion.getValue(QuestionPart.OPTION3), reserveQuestion.getValue(QuestionPart.OPTION4)};
        });
        isRunning = false;
    }
    /////////////////////////////////////////////////////////////
    //                >>>> Member methods.<<<<

    /**
     * It is overriding LifeLine run method.
     *
     * @return string array that have the reserve question and its options.
     */
    String[] run() {
        isRunning = true;
        return super.run();
    }

    /**
     * It uses to know if changeQuestion life line is running
     *
     * @return boolean value of isRunning member variable that show if changeQuestion life line is running.
     */
    boolean isRunning() {
        return isRunning;
    }

    /**
     * It uses to make isRunning member variable equals to false.
     */
    void stop() {
        isRunning = false;
    }

    /**
     * It uses to reset change question life line.
     */
    void reset() {
        stop();
        super.reset();
    }
}
