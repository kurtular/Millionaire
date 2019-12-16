package millionaire.View;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import millionaire.Controller;
import millionaire.Timer;
import millionaire.FINAL_GLOBAL_VARIABLES.SoundEffectName;

/**
 * @author Mohammad
 */

class PlayScreen extends BorderPane {
    private static PlayScreen instance = new PlayScreen();
    private Button withDrawButton;
    private PlayScreen(){
        super();
        ImageView withDrawBkImage = new ImageView("img/withdraw.png");
        withDrawBkImage.setFitWidth(115);
        withDrawBkImage.setFitHeight(50);

        withDrawButton = new Button();
        withDrawButton.setId("withDrawButton");
        withDrawButton.setGraphic(withDrawBkImage);

        enableWithdrawing();

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

    void enableWithdrawing(){
        withDrawButton.setDisable(false);
        withDrawButton.setOnMouseClicked(event -> {
            //
            QuestionArea.getInstance().disableActions();
            LifeLineArea.getInstance().deactivateTemporarily();
            //
            TimerLabel.getInstance().stopTimer();
            //
            Controller.getInstance().endTheGame(true);
        });
    }
    void disableWithdrawing(){
        withDrawButton.setDisable(true);
        withDrawButton.setOpacity(1);
    }
    ///////////////////////////////////////////////////////////////////////
//  addTo()
    static void addTo(Pane pane){
        instance.enableWithdrawing();
        SoundEffectPlayer.play(SoundEffectName.PLAY_SCREEN_INTRO);
        Timer.delay(()->{
            pane.getChildren().clear();
            pane.getChildren().add(getInstance());
        },3);
    }
}