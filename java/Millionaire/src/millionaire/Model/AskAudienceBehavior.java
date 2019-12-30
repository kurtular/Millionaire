package millionaire.Model;

import java.util.ArrayList;

/**
 * @author Jesse, Mohammad
 */

public class AskAudienceBehavior implements LifeLineBehavior {
    private static AskAudienceBehavior instance = new AskAudienceBehavior();

    protected static AskAudienceBehavior getInstance() {
        return instance;
    }

    private AskAudienceBehavior() {
    }

    //Jesse
    @Override
    public String[] toDo() {
        StringBuilder returnedResult = new StringBuilder("Publikens röst: \n");
        char[] options = {'A', 'B', 'C', 'D'};
        ArrayList<Character> shownOptions = new ArrayList<>();
        for (int i = 0; i < options.length; i++) {
            if (game.getQuestionPart((byte) (options[i] - 64)) != null)
                shownOptions.add(options[i]);
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
