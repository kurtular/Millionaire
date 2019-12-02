package millionaire.View;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import millionaire.Controller;

class PlayerNameScreen extends BorderPane { //todo you can change layout type if you want to.
    final private static PlayerNameScreen instance = new PlayerNameScreen();

    static PlayerNameScreen getInstance() {
        return instance;
    }

    private PlayerNameScreen(){
        //todo create this object
    }

    static void addTo(Pane pane){
        pane.getChildren().clear();
        pane.getChildren().add(getInstance());
        //Controller.getInstance().startTheGame(player name ska skickas in här);
        //PlayScreen.addTo(pane);                                               //todo raden ska aktiveras när man trycker på det knappet när spelare har skrivit färdigt in sitt namn.

    }
}
