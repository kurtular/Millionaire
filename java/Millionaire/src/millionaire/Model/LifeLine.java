package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES.*;

/**
 * @author Mohammad
 */
class LifeLine {
    private static LifeLine removeHalf = new LifeLine(RemoveHalfBehavior.getInstance());
    private static LifeLine callAFriend = new LifeLine(CallAFriendBehavior.getInstance());
    private static LifeLine askAudience = new LifeLine(AskAudienceBehavior.getInstance());

    static LifeLine getInstance (String lifeLineType){
       if(lifeLineType.equals(LifeLineType.REMOVE_HALF)){
           return removeHalf;
       }else if (lifeLineType.equals(LifeLineType.CALL_A_FRIEND)){
           return callAFriend;
       }else if (lifeLineType.equals(LifeLineType.ASK_AUDIENCE)){
           return askAudience;
       }else {
           System.err.println("!!There is not a relative LifeLine.!!\nMake sure you send the correct lifeLineType.");
           return null;
       }
    }

    private boolean isUsed;
    private LifeLineBehavior behavior;

    private LifeLine(LifeLineBehavior behavior){
        isUsed=false;
        this.behavior = behavior;
    }

    // The following constructor made specially to be able to create changeQuestion lifeline that extend this class.
    LifeLine(){
        isUsed=false;
        this.behavior = null;
    }

    String [] run(){
        if (!isUsed) {
            isUsed = true;
            return behavior.toDo();
        }else return null;
    }

    void reset(){
         isUsed=false;
    }
    //
    void setBehavior(LifeLineBehavior behavior) {
        if (this.behavior==null){
            this.behavior = behavior;
        }else {
            System.err.println("!!This LifeLine have already a behavior.!!\n You can not change the behavior using this method.");
        }
    }
}
