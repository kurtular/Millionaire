package millionaire;

/**
 * This class is a container to all global variables and it's include other classes (nested classes).<br>
 * This way used to make it easier to understand instead of sending unrelated value.<br>
 * This class is used in different places of this application so make sure that you understand the purpose of this class before using it or editing a part of this application.
 *
 * @author Mohammad, Jesse, Henrik.
 */
public abstract class FINAL_GLOBAL_VARIABLES {
    //           >>>>Class variables and methods.<<<<
    /**
     * It stores all possible prizes.
     */
    private static final String[] PRIZES = {"0", "100", "200", "300", "500", "1 000", "2 000", "4 000", "8 000", "16 000", "32 000", "64 000", "125 000", "250 000", "500 000", "1 000 000"};

    /**
     * It returns a string array(Prizes array). see {@link #PRIZES}
     *
     * @return a string array.
     */
    public static String[] getPRIZES() {
        return PRIZES;
    }

    /**
     * It stores the used currency symbol.(kan be $,kr,sek,£,€)
     */
    private static final String CURRENCY_SYMBOL = "$";

    /**
     * It returns used currency symbol. see {@link #CURRENCY_SYMBOL}
     *
     * @return CURRENCY_SYMBOL value.
     */
    public static String getCurrencySymbol() {
        return CURRENCY_SYMBOL;
    }

    /**
     * It stores question duration (max timer value).
     */
    private static final int QUESTION_DURATION = 30;

    /**
     * It returns QUESTION_DURATION value. see {@link #QUESTION_DURATION}
     *
     * @return a number that refers to question duration.
     */
    public static int getQuestionDuration() {
        return QUESTION_DURATION;
    }
    /////////////////////////////////////////////////////////////
    //              >>>>Nested classes.<<<<

    /**
     * It stores all possible question parts like the question text and its options (alternatives) and the order of the shown question.<br>
     * Every variable name describe the purpose of creating it.
     */
    public abstract static class QuestionPart {
        public final static byte QUESTION_TEXT = 0;
        public final static byte OPTION1 = 1;
        public final static byte OPTION2 = 2;
        public final static byte OPTION3 = 3;
        public final static byte OPTION4 = 4;
        // That doesn't mean that the current question will be 5 always. That mean the programmer refer to the order of the shown question.
        public final static byte CURRENT_QUESTION = 5;
    }

    /**
     * It stores all possible option button states.<br>
     * Every variable name describe the purpose of creating it.
     */
    public abstract static class OptionButtonState {
        public static final int WRONG = -1;
        public static final int CHECKING = 0;
        public static final int CORRECT = 1;
        public static final int DEFAULT = 2;
    }

    /**
     * This class stores the names of the different soundeffects. Its easier to understand the code <br>
     * doing this and its much safer to prevent misspellings.
     */
    public abstract static class SoundEffectName {
        public final static String INTRO = "intro.mp3";
        public final static String PLAY_SCREEN_INTRO = "play_screen_intro.mp3";

        public final static String WRONG_ANSWER = "wrong_Answer.mp3";
        public final static String CHECKING_ANSWER = "checking_answer.mp3";
        public final static String CORRECT_ANSWER = "correct_Answer.mp3";

        public final static String AUDIENCE = "audience.mp3";
        public final static String CALL_FRIEND = "call_friend.mp3";
        public final static String REMOVE_HALF_CHANGE_QUESTION = "remove_half.mp3";
        public final static String FIRST_5_QUESTIONS = "1to5.mp3";
        public final static String SECOND_5_QUESTIONS = "6to10.mp3";
        public final static String ELEVEN = "11.mp3";
        public final static String TWELVE_THIRTEEN = "12&13.mp3";
        public final static String FOURTEEN = "14.mp3";
        public final static String FIFTEEN = "14.mp3";

        public final static String GAME_END = "end.mp3";
    }

    public abstract static class LifeLineType {
        public final static String ASK_AUDIENCE = "audience";
        public final static String CALL_A_FRIEND = "friend";
        public final static String REMOVE_HALF = "half";
        public final static String CHANGE_QUESTION = "change";
    }
}
