package millionaire.Model;

/**
 * Singleton class for call a friend lifeline. It implements LifelineBehavior interface and because of that <br>
 * it must override one method. It creates the object which calculate what answer the "friend" will tell the <br>
 * player. It belongs to the Model.
 *
 * @author Henrik, Mohammad.
 */
class CallAFriendBehavior implements LifeLineBehavior {
    private static final CallAFriendBehavior instance = new CallAFriendBehavior();

    /**
     * The get method will return the reference to call-a-friend object.
     *
     * @return the only instance of this Singleton class.
     */
    protected static CallAFriendBehavior getInstance() {
        return instance;
    }

    /**
     * The constructor creates the only call a friend object.
     */
    private CallAFriendBehavior() {
    }

    /**
     * Lifeline call a friend are taking care of here. 50% of the time you get for sure the right answer.
     * 25% of the time you get the right answer but the friend are in doubt.
     * And in 25% of the cases you get the wrong answer.
     *
     * @return The string to be shown at the screen.
     */
    @Override
    public String[] toDo() {
        String friendSays;
        int rand = (int) (Math.random() * 100);
        //50% of the times your friend is completely sure what the answer is.
        //  Because of "game.getCorrectAnswerSymbol" returns a char and "getQuestionPart" need a byte the method subtract 64 using ASCInumbers.
        if (rand > 50) {
            friendSays = "Jag är säker på att det är: " + game.getCorrectAnswerSymbol() + ". " + game.getQuestionPart((byte) (game.getCorrectAnswerSymbol() - 64));
        }
        // 25% the friend is pretty sure and guessing at the right answer.
        else if (rand > 25) {
            friendSays = "Jag TROR att det är: " + game.getCorrectAnswerSymbol() + ". " + game.getQuestionPart((byte) (game.getCorrectAnswerSymbol() - 64));
        }
        //25% the friend is pretty sure but guessing at the wrong answer.
        else {
            boolean isAnswerCorrect;
            while (true) {
                int i = (int) (Math.random() * 4 + 1);
                isAnswerCorrect = game.checkAnswer((char) (i + 64));
                String answerText = game.getQuestionPart((byte) (i));
                if (!isAnswerCorrect && answerText != null) {
                    friendSays = "Jag TROR att det är: " + (char) (i + 64) + ". " + answerText;
                    break;
                }
            }
        }
        return new String[]{"Din vän säger i telefonen:\n" + friendSays};
    }
}
