package millionaire.View;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import millionaire.Controller;
import millionaire.Timer;
import millionaire.FINAL_GLOBAL_VARIABLES.SoundEffectName;

/**
 * It represents the main playScreen (There will be shown a question with its alternatives).
 *
 * @author Mohammad, Henrik.
 */
class PlayScreen extends BorderPane {
    //           >>>>Class variables and methods.<<<<
    // The following variable and method created to apply singleton design pattern.
    /**
     * It is the only instance of this class (singleton).
     */
    private static final PlayScreen instance = new PlayScreen();

    /**
     * It returns PlayScreen object.
     *
     * @return the only possible instance of this class (singleton).
     */
    static PlayScreen getInstance() {
        return instance;
    }

    /////////////////////////////////////////////////////////////
    //              >>>>Member variables.<<<<
    /**
     * It is withdrawal button.
     */
    private final Button withDrawButton;

    ////////////////////////////////////////////////////////////
    //              >>>>Class constructor.<<<<

    /**
     * It is a constructor method creates a PlayScreen object.
     */
    private PlayScreen() {
        // call BorderPane constructor and enable it.
        super();
        // Setup withdraw button.
        ImageView withDrawBkImage = new ImageView("img/withdraw.png");
        withDrawBkImage.setFitWidth(115);
        withDrawBkImage.setFitHeight(50);
        withDrawButton = new Button();
        withDrawButton.setId("withDrawButton");
        withDrawButton.setGraphic(withDrawBkImage);
        enableWithdrawing();
        // Order PlayScreen's content positions.
        setLeft(withDrawButton);
        setCenter(LifeLineArea.getInstance());
        setBottom(QuestionArea.getInstance());
        setRight(PriceTable.getInstance());
        this.setPrefSize(1024, 768);
    }
    /////////////////////////////////////////////////////////////
    //                >>>> Member methods.<<<<

    /**
     * Creating the action event to withdrawal button and enabling the button.
     */
    void enableWithdrawing() {
        withDrawButton.setDisable(false);
        withDrawButton.setOnMouseClicked(event -> {
            // Disable QuestionArea and LifeLineArea.
            QuestionArea.getInstance().disableActions();
            LifeLineArea.getInstance().deactivateTemporarily();
            // Stop timer.
            TimerLabel.getInstance().stopTimer();
            // End the game.
            Controller.getInstance().endTheGame(true);
        });
    }

    /**
     * Temporarily disabling withDrawButton and changing its opacity.
     */
    void disableWithdrawing() {
        withDrawButton.setDisable(true);
        withDrawButton.setOpacity(1);
    }

    /**
     * It shows game play screen.
     */
    static void show() {
        instance.enableWithdrawing();
        SoundEffectPlayer.play(SoundEffectName.PLAY_SCREEN_INTRO);
        Timer.delay(() -> {
            Gui.content.getChildren().clear();
            Gui.content.getChildren().add(instance);
            // the timer resets usually by Gui while updating the shown question but the following lines is wrote to fix a bug that show the timer have spent 3 seconds already because of the current delay.
            TimerLabel.getInstance().resetTimer();
            Timer.delay(()->TimerLabel.getInstance().startTimer(), 0.1);
            SoundEffectPlayer.play(SoundEffectName.FIRST_5_QUESTIONS);
        }, 3);
    }
}