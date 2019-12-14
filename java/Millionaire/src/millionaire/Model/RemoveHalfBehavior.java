package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES.*;

class RemoveHalfBehavior implements LifeLineBehavior {
    private static RemoveHalfBehavior instance = new RemoveHalfBehavior();
    protected static RemoveHalfBehavior getInstance(){return instance;}
    private RemoveHalfBehavior(){}
    @Override
    public String[] toDo() {
        byte currentQuestion;
        if (!game.changeQuestion.isRunning()) {
            currentQuestion = game.currentQuestion;
        } else {
            currentQuestion = 0;
        }
        //
        byte latestFoundedWrongOptionIndex=-1;
        for (int i = 0; i<2 ; i++){
            boolean foundWrongOption = false;
            while(!foundWrongOption){
                byte rand = (byte)(Math.random()*4+1);
                while (rand == latestFoundedWrongOptionIndex){
                    rand = (byte)(Math.random()*4+1);
                }
                if (!game.checkAnswer((char) (rand + 64))) {
                    game.questions[currentQuestion].removeOption((rand));
                    foundWrongOption = true;
                    latestFoundedWrongOptionIndex =rand;
                }
            }
        }
        String[] returnedResult = {game.getQuestionPart(QuestionPart.QUESTION_TEXT), game.getQuestionPart(QuestionPart.OPTION1), game.getQuestionPart(QuestionPart.OPTION2), game.getQuestionPart(QuestionPart.OPTION3), game.getQuestionPart(QuestionPart.OPTION4)};
        return returnedResult;
    }
}
