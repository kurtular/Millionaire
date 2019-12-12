package millionaire.Model;

class LifeLine {
    private boolean isUsed;
    LifeLineBehavior behavior;

    LifeLine(LifeLineBehavior behavior){
        isUsed=false;
        this.behavior = behavior;
    }

    String [] run(){
        if (!isUsed) {
            isUsed = true;
            return behavior.toDo();
        }else return null;
    }

    boolean isUsed(){
        return isUsed;
    }
}
