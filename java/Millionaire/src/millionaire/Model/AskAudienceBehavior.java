package millionaire.Model;

import java.util.ArrayList;

/**
 * AskAudienceBehavior class implements a behavior from interface(LifeLineBehavior). By implementing LifeLineBehavior we need to override toDomethod.
 * toDomethod decide what is the behavior of AskAudienceBehavior object.
 * The following class is using singleton pattern which can only generate single object from this class.
 * The only way to get the object of this class is by getInstance() method. This method is protected and static.
 * AskAudienceBehavior has a private static class variable
 *
 * @author Jesse, Mohammad
 * @version 1.0
 */

class AskAudienceBehavior implements LifeLineBehavior {
    private static final AskAudienceBehavior instance = new AskAudienceBehavior();

    protected static AskAudienceBehavior getInstance() {
        return instance;
    }

    private AskAudienceBehavior() {
    }

    //Jesse

    /**
     * This method will randomly give a vote to each alternatives (A, B, C, D).
     * the first for-loop will add alternatives to ArrayList
     * the second for-loop below will randomly generate a random number for each percent[i]. Pay attention to else-scoop where the max value will decrease to keep the number within 100 where it represent 100%.
     * the third for-loop will look for a correct answer.
     * in the if-else decide if the rand is less than 75(represent 75%) means majority of public sure of the highest voted is correct else they are note sure.
     * the last for-loop will add extra string to the returnedResult.
     *
     * @return an array of string that show the percent of each alternative that has been voted by audience
     */
    @Override
    public String[] toDo() {
        StringBuilder returnedResult = new StringBuilder("Publikens röst: \n");
        char[] options = {'A', 'B', 'C', 'D'};
        ArrayList<Character> shownOptions = new ArrayList<>();
        for (char option : options) {
            if (game.getQuestionPart((byte) (option - 64)) != null)
                shownOptions.add(option);
        }
        int rand = (int) (Math.random() * 100 + 1);
        int max = 100;
        int[] percent = new int[shownOptions.size()];
        byte correctAnswerIndex = 0;

        for (int i = 0; i < shownOptions.size(); i++) {
            if (i == shownOptions.size() - 1) {
                percent[i] = max;
            } else {
                percent[i] = (int) Math.round(Math.random() * max);
                max -= percent[i];
            }
        }
// find the correct answer.
        for (byte i = 0; i < shownOptions.size(); i++) {
            if (game.checkAnswer(shownOptions.get(i))) {
                correctAnswerIndex = i;
            }
        }
        if (rand < 75) {
            for (int i = 0; i < percent.length; i++) {
                if (percent[correctAnswerIndex] < percent[i]) {
                    int temp = percent[correctAnswerIndex];
                    percent[correctAnswerIndex] = percent[i];
                    percent[i] = temp;

                }
            }
        } else {
            for (int i = 0; i < percent.length; i++) {
                if (percent[correctAnswerIndex] > percent[i]) {
                    int temp = percent[correctAnswerIndex];
                    percent[correctAnswerIndex] = percent[i];
                    percent[i] = temp;
                    break;
                }
            }
        }

        for (byte j = 0, i = 0; i < options.length; i++) {
            if (shownOptions.size() != options.length) {
                if (j < shownOptions.size() && options[i] == shownOptions.get(j)) {
                    returnedResult.append(shownOptions.get(j)).append(" : ").append(percent[j]).append("% ");
                    j++;
                } else {
                    returnedResult.append(options[i]).append(" : 0%");
                }

            } else {
                returnedResult.append(shownOptions.get(i)).append(" : ").append(percent[i]).append("% ");
            }
        }
        return new String[]{returnedResult.toString()};
    }
}
