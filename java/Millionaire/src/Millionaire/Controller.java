package Millionaire;

import Millionaire.Model.Game;
import Millionaire.View.Gui;

public class Controller {
    Game game;
    Gui gui;
    Controller(String args[]){
        game = new Game();
        gui = new Gui();
        gui.launchGui(args);
    }
}
