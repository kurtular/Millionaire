package millionaire.View;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import millionaire.Controller;
import millionaire.Timer;


class PlayScreen extends BorderPane {
    private static PlayScreen instance = new PlayScreen();
    private PlayScreen(){
        super();
        ImageView withDrawBkImage = new ImageView("img/withdraw.png");
        withDrawBkImage.setFitWidth(115);
        withDrawBkImage.setFitHeight(50);
        Button withDrawButton = new Button();
        withDrawButton.setId("withDrawButton");
        withDrawButton.setGraphic(withDrawBkImage);

        withDrawButton.setOnMouseClicked(event -> {
            //
            TimerLabel.getInstance().stopTimer();
            //
            Controller.getInstance().endTheGame(true);
        });

// Create the question area (the contain the question text with it's option and the current balance that player have under the competition)
        setLeft(withDrawButton);
        setCenter(LifeLineArea.getInstance());
        setBottom(QuestionArea.getInstance());
        setRight(PriceTable.getInstance());
        this.setPrefSize(1024, 768);
    }

    //
    public static PlayScreen getInstance(){
        return instance;
    }

    ///////////////////////////////////////////////////////////////////////
//  addTo()
    static void addTo(Pane pane){
        SoundEffect.play(SoundEffect.PLAY_SCREEN_INTRO);
        Timer.delay(()->{
            pane.getChildren().clear();
            pane.getChildren().add(getInstance());
        },3);

        // TODO: Decide when PlayScreen move to EndGameScreen.
    }
}