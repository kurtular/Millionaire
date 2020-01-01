package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES;

/**
 * @author Joakim, Mohammad.
 */
class Player {
    //
    private static final Player instance = new Player();

    static Player getInstance() {
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

    int getScore() {
        return score;
    }

    void addToScore(int seconds, int currentBalance) {
        score += currentBalance + (currentBalance / 2 * (seconds / (double) FINAL_GLOBAL_VARIABLES.getQuestionDuration()));
    }

    void resetPlayer() {
        name = "";
        score = 0;
    }
}
