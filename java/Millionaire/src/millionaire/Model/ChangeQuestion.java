package millionaire.Model;

class ChangeQuestion extends LifeLine {
    private static ChangeQuestion instance = new ChangeQuestion();
    protected static ChangeQuestion getInstance(){return instance;}
    private boolean isRunning;
    private ChangeQuestion(){
        //
        super(() -> {
            Question reserveQuestion = LifeLineBehavior.game.questions[0];
            String[] returnedResult = {reserveQuestion.getValue(LifeLineBehavior.game.QUESTION_TEXT),reserveQuestion.getValue(LifeLineBehavior.game.OPTION1), reserveQuestion.getValue(LifeLineBehavior.game.OPTION2),reserveQuestion.getValue(LifeLineBehavior.game.OPTION3),reserveQuestion.getValue(LifeLineBehavior.game.OPTION4)};
            return returnedResult;
        });
        isRunning=false;
    }
//
    String [] run() {
        isRunning=true;
        return super.run();
    }
//
    boolean isRunning() {
        return isRunning;
    }
    void stop(){
        isRunning =false;
    }
}
