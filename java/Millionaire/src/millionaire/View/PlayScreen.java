package millionaire.View;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

class PlayScreen extends BorderPane {
    private static PlayScreen instance = new PlayScreen();
    private PlayScreen(){
        super();

// Create the question area (the contain the question text with it's option and the current balance that player have under the competition)

        setCenter(LifeLineArea.getInstance());
        setBottom(QuestionArea.getInstance());
        setRight(PriceTable.getInstance());
        this.setPrefSize(1024, 768);
        //TODO bring the other parts
    }

//
    public static PlayScreen getInstance(){
      return instance;
    }

    ///////////////////////////////////////////////////////////////////////
//  addTo()
    static void addTo(Pane pane){
        pane.getChildren().clear();
        pane.getChildren().add(getInstance());
        // TODO: Decide when PlayScreen move to EndGameScreen.
    }
}