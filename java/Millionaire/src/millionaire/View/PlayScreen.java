package millionaire.View;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import millionaire.Controller;


class PlayScreen extends BorderPane {
    private static PlayScreen instance = new PlayScreen();
    private ImageView withDraw;
    private Label withDrawLabel;
    private PlayScreen(){
        super();
        withDraw = new ImageView("img/withdraw.png");
        withDraw.setFitWidth(75);
        withDraw.setFitHeight(50);
        withDrawLabel = new Label();
        withDrawLabel.setGraphic(withDraw);
        withDrawLabel.setCursor(Cursor.HAND);
        withDrawLabel.setPadding(new Insets(20,20,20,20));
        withDrawLabel.setOnMouseClicked(event -> {
            Controller.getInstance().endTheGame(true);
        });

// Create the question area (the contain the question text with it's option and the current balance that player have under the competition)
        setLeft(withDrawLabel);
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
        SoundEffect.play(SoundEffect.PLAY_SCREEN_INTRO);
        pane.getChildren().clear();
        pane.getChildren().add(getInstance());
        // TODO: Decide when PlayScreen move to EndGameScreen.
    }
}