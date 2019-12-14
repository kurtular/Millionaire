package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES.*;

class ChangeQuestion extends LifeLine {
    private static ChangeQuestion instance = new ChangeQuestion();
    static ChangeQuestion getInstance(){return instance;}

    private boolean isRunning;
    private ChangeQuestion(){
        //
        super();
        // Set the behavior of change question life line.
        setBehavior(() -> {
            Question reserveQuestion = LifeLineBehavior.game.questions[0];
            return new String[]{reserveQuestion.getValue(QuestionPart.QUESTION_TEXT),reserveQuestion.getValue(QuestionPart.OPTION1), reserveQuestion.getValue(QuestionPart.OPTION2),reserveQuestion.getValue(QuestionPart.OPTION3),reserveQuestion.getValue(QuestionPart.OPTION4)};
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

    void reset(){
        stop();
        super.reset();
    }
}
