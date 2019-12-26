package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES;
import millionaire.FINAL_GLOBAL_VARIABLES.*;

/**
 * @author Mohammad
 * This class represents life lines that can be used of a player
 */
class LifeLine {
    //           >>>>Class variables and methods.<<<<
    // The following variables and methods created to apply singleton design pattern.
    private static LifeLine removeHalf = new LifeLine(RemoveHalfBehavior.getInstance());
    private static LifeLine callAFriend = new LifeLine(CallAFriendBehavior.getInstance());
    private static LifeLine askAudience = new LifeLine(AskAudienceBehavior.getInstance());

    /**
     * It will return a life line.
     *
     * @param LifeLineType is the type of life line that will be returned.
     * @return a life line depending on received LifeLineType value.
     */
    static LifeLine getInstance(String LifeLineType) {
        if (LifeLineType.equals(FINAL_GLOBAL_VARIABLES.LifeLineType.REMOVE_HALF)) {
            return removeHalf;
        } else if (LifeLineType.equals(FINAL_GLOBAL_VARIABLES.LifeLineType.CALL_A_FRIEND)) {
            return callAFriend;
        } else if (LifeLineType.equals(FINAL_GLOBAL_VARIABLES.LifeLineType.ASK_AUDIENCE)) {
            return askAudience;
        } else {
            System.err.println("!!There is not a relative LifeLine.!!\nMake sure you send the correct lifeLineType.");
            return null;
        }
    }

    /////////////////////////////////////////////////////////////
    //              >>>>Member variables.<<<<
    //isUsed will store the status of a life line if it used or not during a game.
    private boolean isUsed;
    // behavior store the behavior (the action that will applied when a player use it) of a life line.
    private LifeLineBehavior behavior;
    ////////////////////////////////////////////////////////////
    //              >>>>Class constructors.<<<<

    /**
     * a constructor method that will create a lifeline.
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
