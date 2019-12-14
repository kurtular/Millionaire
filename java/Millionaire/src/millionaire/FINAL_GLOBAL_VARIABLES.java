package millionaire;

public abstract class FINAL_GLOBAL_VARIABLES {

    private static final String[] PRIZES = {"0", "100", "200", "300", "500", "1 000", "2 000", "4 000", "8 000", "16 000", "32 000", "64 000", "125 000", "250 000", "500 000", "1 000 000"};

    public static String[] getPRIZES() {
        return PRIZES;
    }

    private static final String CURRENCY_SYMBOL = "$";

    public static String getCurrencySymbol() {
        return CURRENCY_SYMBOL;
    }

    private static final int QUESTION_DURATION = 30;

    public static int getQuestionDuration() {
        return QUESTION_DURATION;
    }

    public abstract static class QuestionPart {
        public final static byte QUESTION_TEXT = 0;
        public final static byte OPTION1 = 1;
        public final static byte OPTION2 = 2;
        public final static byte OPTION3 = 3;
        public final static byte OPTION4 = 4;
        public final static byte CURRENT_QUESTION = 5;
    }

    public abstract static class OptionButtonState {
        public static final int WRONG = -1;
        public static final int CHECKING = 0;
        public static final int CORRECT = 1;
        public static final int DEFAULT = 2;
    }


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
