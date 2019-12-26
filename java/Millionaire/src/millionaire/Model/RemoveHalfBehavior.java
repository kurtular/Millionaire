package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES.*;

/**
 * @author Mohammad.
 * This class represents remove half life line behavior.
 * It is created to apply strategy design pattern so it implements LifeLineBehavior inteface.
 */
class RemoveHalfBehavior implements LifeLineBehavior {
    //           >>>>Class variable and method.<<<<
    // The following variable and method created to apply singleton design pattern.
    private static RemoveHalfBehavior instance = new RemoveHalfBehavior();
    /**
     * It will return remove half life line behavior.
     * @return the only possible instance of this class (singleton).
     */
    protected static RemoveHalfBehavior getInstance() {
        return instance;
    }
    ////////////////////////////////////////////////////////////
    //              >>>>Class constructor.<<<<
    private RemoveHalfBehavior() {}

    /////////////////////////////////////////////////////////////
    //                >>>> Member methods.<<<<

    //todo Ask Rickard if we need to write javadoc for overridden methods.
    @Override
    public String[] toDo() {
        byte currentQuestion;
        if (!game.changeQuestion.isRunning()) {
            currentQuestion = game.currentQuestion;
        } else {
            currentQuestion = 0;
        }
        // -1 is not meaningful.
        byte latestFoundedWrongOptionIndex = -1;
        for (int i = 0; i < 2; i++) {
            boolean foundWrongOption = false;
            while (!foundWrongOption) {
                byte rand = (byte) (Math.random() * 4 + 1);
                while (rand == latestFoundedWrongOptionIndex) {
                    rand = (byte) (Math.random() * 4 + 1);
                }
                if (!game.checkAnswer((char) (rand + 64))) {
                    game.questions[currentQuestion].removeOption((rand));
                    foundWrongOption = true;
                    latestFoundedWrongOptionIndex = rand;
                }
            }
        }
        String[] returnedResult = {game.getQuestionPart(QuestionPart.QUESTION_TEXT), game.getQuestionPart(QuestionPart.OPTION1), game.getQuestionPart(QuestionPart.OPTION2), game.getQuestionPart(QuestionPart.OPTION3), game.getQuestionPart(QuestionPart.OPTION4)};
        return returnedResult;
    }
}
