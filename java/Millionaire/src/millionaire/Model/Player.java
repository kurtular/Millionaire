package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES;

/**
 * @author Joakim, Mohammad.
 */
public class Player {
    //
    private static Player instance = new Player();

    public static Player getInstance() {
        return instance;
    }

    private String name;
    private int score;

    private Player() {
        resetPlayer();
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addToScore(int seconds, int currentBalance) {
        score += currentBalance + (currentBalance / 2 * (seconds / (double) FINAL_GLOBAL_VARIABLES.getQuestionDuration()));
    }

    void resetPlayer() {
        name = "";
        score = 0;
    }
}
