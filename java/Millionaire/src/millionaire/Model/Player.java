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
     * Get instance for singleton. returns instance of class, since singleton.
     * @return
     */
    static Player getInstance() {
        return instance;
    }
    private String name;
    private int score;

    /**
     * Constructor that calls method resetPlayer.
     */
    private Player() {
        resetPlayer();
    }

    void setName(String name) {
        this.name = name;
    }

    /**
     * getter
     * @return
     */
    String getName() {
        return name;
    }

    /**
     * getter
     * @return
     */
    int getScore() {
        return score;
    }

    /**
     * Method that calculates player score using seconds and current balance.
     * @param seconds
     * @param currentBalance
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
