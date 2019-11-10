package Millionaire;

import Millionaire.Model.Game;
import Millionaire.View.Gui;
import Millionaire.View.GuiListners;
import Millionaire.View.OptionButton;
import Millionaire.View.QuestionArea;
import javafx.scene.Parent;


public class Controller {
    Game game;
    Gui gui;
    Controller(String args[]){
        gui = new Gui();
        gui.setEventsListner(guiListners());
        gui.launchGui(args);
        game = new Game();
    }
    private GuiListners guiListners(){
        GuiListners guiListners = new GuiListners() {

            @Override
            public void setAnswer(QuestionArea questionArea,char buttonSymbol) {
                System.out.println(buttonSymbol);

            }

            @Override
            public void changeContent(Parent content) {
                System.out.println("g");
            }
        };
        return guiListners;
    }
}
