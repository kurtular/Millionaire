package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES;

/**
 * This class represents life lines that can be used of a player
 *
 * @author Mohammad.
 */
class LifeLine {
    //           >>>>Class variables and methods.<<<<
    // The following variables and methods created to apply singleton design pattern.
    /**
     * It is the only instance of this removeHalf life line (singleton).
     */
    private static final LifeLine removeHalf = new LifeLine(RemoveHalfBehavior.getInstance());
    /**
     * It is the only instance of this callAFriend life line (singleton).
     */
    private static final LifeLine callAFriend = new LifeLine(CallAFriendBehavior.getInstance());
    /**
     * It is the only instance of this askAudience life line (singleton).
     */
    private static final LifeLine askAudience = new LifeLine(AskAudienceBehavior.getInstance());

    /**
     * It will return a life line.
     *
     * @param LifeLineType is the type of life line that will be returned.
     * @return a life line depending on received LifeLineType value.
     */
    static LifeLine getInstance(String LifeLineType) {
        switch (LifeLineType) {
            case FINAL_GLOBAL_VARIABLES.LifeLineType.REMOVE_HALF:
                return removeHalf;
            case FINAL_GLOBAL_VARIABLES.LifeLineType.CALL_A_FRIEND:
                return callAFriend;
            case FINAL_GLOBAL_VARIABLES.LifeLineType.ASK_AUDIENCE:
                return askAudience;
            default:
                System.err.println("!!There is not a relative LifeLine.!!\nMake sure you send the correct lifeLineType.");
                return null;
        }
    }

    /////////////////////////////////////////////////////////////
    //              >>>>Member variables.<<<<
    /**
     * It stores the status of a life line if it used or not during a game.
     */
    private boolean isUsed;
    /**
     * It stores the behavior (the action that will applied when a player use it) of a life line.
     */
    private LifeLineBehavior behavior;
    ////////////////////////////////////////////////////////////
    //              >>>>Class constructors.<<<<

    /**
     * It is a constructor method creates a ChangeQuestion object.
     *
     * @param behavior is a behavior that the created life line will have. (Used strategy design pattern)
     */
    private LifeLine(LifeLineBehavior behavior) {
        isUsed = false;
        this.behavior = behavior;
    }

    /**
     * This constructor made specially to create changeQuestion lifeline that extends this class.
     */
    LifeLine() {
        isUsed = false;
        this.behavior = null;
    }
    /////////////////////////////////////////////////////////////
    //                >>>> Member methods.<<<<

    /**
     * It will change isUsed variable to true if the life line is not used yet and run life line behavior that will return a string array as a result.
     *
     * @return string array that will be shown after using a life line.
     */
    String[] run() {
        if (!isUsed) {
            isUsed = true;
            return behavior.toDo();
        } else return null;
    }

    /**
     * It will reset isUsed value to false. (Used to start a new game without exit or end the game)
     */
    void reset() {
        isUsed = false;
    }

    /**
     * It will set a behavior to a life line that don't have a behavior. (made specially to set changeQuestion lifeline behavior. )
     *
     * @param behavior is a behavior that the created life line will have.
     */
    void setBehavior(LifeLineBehavior behavior) {
        if (this.behavior == null) {
            this.behavior = behavior;
        } else {
            System.err.println("!!This LifeLine have already a behavior.!!\n You can not change the behavior using this method.");
        }
    }
}
