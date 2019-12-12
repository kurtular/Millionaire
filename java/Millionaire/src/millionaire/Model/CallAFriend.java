package millionaire.Model;

class CallAFriend implements LifeLineBehavior{
    private static CallAFriend instance = new CallAFriend();
    protected static CallAFriend getInstance(){return instance;}
    private CallAFriend(){}

    @Override
    public String[] toDo() {
        String friendSays = "";
        int rand = (int) (Math.random() * 100);

        //50% of the times your friend is completely sure what the answer is.
        if (rand > 50) {
            friendSays = "Jag är säker på att det är: " + game.getCorrectAnswerSymbol() + ". " + game.getValue((byte) (game.getCorrectAnswerSymbol() - 64));  //  Beacuse of "i" is a char and getValue of the answer demand a byte the method subtract 64 using ASCInumbers.
        }
        // 25% the friend is pretty sure and guessing at the right answer.
        else if (rand > 25) {
            friendSays = "Jag TROR att det är: " + game.getCorrectAnswerSymbol() + ". " + game.getValue((byte) (game.getCorrectAnswerSymbol() - 64));
        }
        //25% the friend is pretty sure but guessing at the wrong answer.
        else {
            boolean isAnswerCorrect;
            while (true){
                int i =(int) (Math.random()*4+1);
                isAnswerCorrect = game.checkAnswer((char)(i+64));
                String answerText = game.getValue((byte) (i));
                if (!isAnswerCorrect && answerText!=null) {
                    friendSays = "Jag TROR att det är: " + (char)(i+64) + ". " + answerText;
                    break;
                }
            }
        }
        return new String[]{"Din vän säger i telefonen:\n" + friendSays};
    }
}
