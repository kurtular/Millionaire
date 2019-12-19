package millionaire.View;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import millionaire.Controller;
import millionaire.Timer;
import millionaire.FINAL_GLOBAL_VARIABLES.SoundEffectName;

/**
 * @author Mohammad, Henrik
 * A singleton class for showing the main playscreen.
 */
class PlayScreen extends BorderPane {
    private static PlayScreen instance = new PlayScreen();
    private Button withDrawButton;

    /**
     * The constructor creating the withdrawalbutton and putting the other parts of the playscreen together.
     */
    private PlayScreen(){
        super();
        ImageView withDrawBkImage = new ImageView("img/withdraw.png");
        withDrawBkImage.setFitWidth(115);
        withDrawBkImage.setFitHeight(50);

        withDrawButton = new Button();
        withDrawButton.setId("withDrawButton");
        withDrawButton.setGraphic(withDrawBkImage);

        enableWithdrawing();

        setLeft(withDrawButton);
        setCenter(LifeLineArea.getInstance());
        setBottom(QuestionArea.getInstance());
        setRight(PriceTable.getInstance());
        this.setPrefSize(1024, 768);
    }

    /**
     * A getter
     * @return the object
     */
    public static PlayScreen getInstance(){
        return instance;
    }

    /**
     * Creating the actionevents to withdrawalbutton and enabling the button.
     */
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

    /**
     * Temporarily disabling the button and chenging the opacity.
     */
    void disableWithdrawing(){
        withDrawButton.setDisable(true);
        withDrawButton.setOpacity(1);
    }

    /**
     * Clearing the aliasscreen and showing the playscreen. Playing a sound and enable withdrawal.
     * @param pane The aliasscreen pane
     */
    static void addTo(Pane pane){
        instance.enableWithdrawing();
        SoundEffectPlayer.play(SoundEffectName.PLAY_SCREEN_INTRO);
        Timer.delay(()->{
            pane.getChildren().clear();
            pane.getChildren().add(getInstance());
        },3);
    }
}