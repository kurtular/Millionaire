package Millionaire;

import Millionaire.Model.Game;
import Millionaire.View.Gui;
import Millionaire.View.GuiListners;
import Millionaire.View.OptionButton;
import Millionaire.View.QuestionArea;

// Controller class ( MVC design pattern).
// game variable represents the Model of MVC design pattern.
// gui variable represents the View of MVC design pattern.
public class Controller {
// Controller member variables.
    private Game game;
    private Gui gui;

///////////////////////////////////////////////////////////
// Controller constructor.
    Controller(String args[]){
        gui = new Gui();
        gui.setEventsListner(setGuiListners());
        gui.launchGui(args);
    }

///////////////////////////////////////////////////////////
// setGuiListners() returns definition of  GuiListners (interface) that will be called from other component that is inside view.
// ((I using this way to be able to make the communication between the View and Model inside the Controller depending on the actions type)).

    private static GuiListners setGuiListners(){
        GuiListners guiListners = new GuiListners() {
// setAnswer() will be called when the player will select an answer for the question.
// It's make some changes that fits the situation for example: making the background color of the selection button yellow and disable the other selection.
            @Override
            public void setAnswer(QuestionArea questionArea,char buttonSymbol) {
                System.out.println(buttonSymbol);
                questionArea.setOptionButtonState(buttonSymbol, OptionButton.Checking);
                questionArea.disableActions();
            }};
        return guiListners;
    }
}