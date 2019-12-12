package millionaire.Model;

class RemoveHalf implements LifeLineBehavior {
    private static RemoveHalf instance = new RemoveHalf();
    protected static RemoveHalf getInstance(){return instance;}
    private RemoveHalf(){}
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
        String[] returnedResult = {game.getValue(game.QUESTION_TEXT), game.getValue(game.OPTION1), game.getValue(game.OPTION2), game.getValue(game.OPTION3), game.getValue(game.OPTION4)};
        return returnedResult;
    }
}
