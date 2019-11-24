package Millionaire.View;

import javafx.scene.Parent;

public interface EventsListner{
// startTheGame will called when the player click on start button (that is shown at the intro).
    public void startTheGame();
// setAnswer() will be fired the game behavior when the player select an answer for a question.
    public void setAnswer(QuestionArea questionArea,char buttonSymbol);
    public void setLifeLine(LifeLineArea lifeLineArea,String lifeLineSelection);

}
