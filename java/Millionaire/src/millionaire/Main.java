package millionaire;

import millionaire.Model.Game;
import millionaire.View.Gui;

class Main {
    public static void main(String[] args) {
        //Create a controller.
//        Timer.delay(5);
        Game game = Game.getInstance();
        Gui gui = new Gui();
        Controller.setupController(game,gui);
        gui.launchGui(args);
    }
}
