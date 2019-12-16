package millionaire;

import millionaire.Model.Game;
import millionaire.View.Gui;

/**
 * @author Joakim
 * @version 1.0
 */
class Main {
    public static void main(String[] args) {
        //Create a controller.
        Game game = Game.getInstance();
        Gui gui = new Gui();
        Controller.setupController(game,gui);
        gui.launchGui(args);
    }
}
