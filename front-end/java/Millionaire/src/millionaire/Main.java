package millionaire;

import millionaire.Model.Game;
import millionaire.View.Gui;

/**
 * @author Joakim
 * @version 1.0
 */

/**
 * Main method. Creates the mvc Connection and starts the GUI.
 */
class Main {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        Gui gui = new Gui();
        Controller.setupController(game,gui);
        gui.launchGui(args);
    }
}
