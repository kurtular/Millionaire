package millionaire;

public abstract class FINAL_GLOBAL_VARIABLES {
    private static final String[] PRIZES =  {"0","100", "200", "300", "500", "1 000", "2 000", "4 000", "8 000", "16 000", "32 000", "64 000", "125 000", "250 000", "500 000", "1 000 000"};
    public static String[] getPRIZES() {
        return PRIZES;
    }

    private static final String CURRENCY_SYMBOL = "$";
    public static String getCurrencySymbol() {
        return CURRENCY_SYMBOL;
    }

    private static final int QUESTION_DURATION  = 30;
    public static int getQuestionDuration() {
        return QUESTION_DURATION;
    }

}
