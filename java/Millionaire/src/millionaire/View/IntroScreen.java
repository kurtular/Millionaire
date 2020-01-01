package millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import millionaire.FINAL_GLOBAL_VARIABLES.SoundEffectName;
import millionaire.Timer;

/**
 * It represnts IntroScreen (will be shown at application start).
 *
 * @author Mohammad.
 */
class IntroScreen extends BorderPane {
    //           >>>>Class variable.<<<<
    // The following variable and method created to apply singleton design pattern.
    /**
     * It is the only instance of this class (singleton).
     */
    private static final IntroScreen instance = new IntroScreen();

    /**
     * It will return IntroScreen object.
     *
     * @return the only possible instance of this class (singleton).
     */
    static IntroScreen getInstance() {
        return instance;
    }

    /////////////////////////////////////////////////////////////
    //              >>>>Member variables.<<<<
    /**
     * It is the new game button.
     */
    private final OptionButton newGame;
    ////////////////////////////////////////////////////////////
    //              >>>>Class constructor.<<<<

    /**
     * It is a constructor method creates an IntroScreen object.
     */
    private IntroScreen() {
        super();
        // Setup the logo image that will shown in intro screen.
        ImageView logo = new ImageView();
        logo.setFitWidth(500);
        logo.setFitHeight(500);
        setTop(logo);
        setAlignment(logo, Pos.TOP_CENTER);
        Timer.delay(() -> logo.setImage(new Image("img/logo1.gif")), 0.150);

        // Change logo image after 4,5 seconds.
        Timer.delay(() -> logo.setImage(new Image("img/logo2.gif")), 4.5);

        // Create and add a new game button with its style and action commands ( runs when it will be clicked)
        newGame = new OptionButton();
        newGame.setText("Nytt spel");
        setBottom(newGame);
        setAlignment(newGame, Pos.BOTTOM_CENTER);
        setPadding(new Insets(25, 0, 50, 0));
        setPrefSize(1024, 768);
    }
    /////////////////////////////////////////////////////////////
    //                >>>> Member methods.<<<<

    /**
     * It shows game intro screen.
     */
    void show() {
        Gui.content.getChildren().clear();
        SoundEffectPlayer.play(SoundEffectName.INTRO);
        Gui.content.getChildren().add(instance);
        // set new game button's action.
        instance.newGame.setOnMouseClicked(event -> AliasScreen.show());
    }
}
