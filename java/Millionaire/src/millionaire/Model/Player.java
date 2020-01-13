package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES;

/**
 * @author Joakim, Mohammad.
 */
class Player {
    /**
     * Constructor
     */
    private static final Player instance = new Player();

    /**
     * It returns player object.
     *
     * @return the only possible instance of this class (singleton).
     */
    static Player getInstance() {
        return instance;
    }
    private String name;
    private int score;

    /**
     * Constructor that calls method resetPlayer..
     */
    private Player() {
        resetPlayer();
    }

    /**
     * player name setter
     * @param name player name
     */
    void setName(String name) {
        this.name = name;
    }
    /**
     * player name getter.
     * @return player name.
     */
    String getName() {
        return name;
    }
    /**
     * player score getter
     * @return player score
     */
    int getScore() {
        return score;
    }
    /**
     * Method that increase player score using seconds and current balance.
     * @param seconds the remaining seconds of QUESTION_DURATION.
     * @param currentBalance the current balance the player earned.
     */
    void addToScore(int seconds, int currentBalance) {
        score += currentBalance + (currentBalance / 2 * (seconds / (double) FINAL_GLOBAL_VARIABLES.getQuestionDuration()));
    }
    /**
     * method that resets player info.
     */
    void resetPlayer() {
        name = "";
        score = 0;
    }
}
