package millionaire.View;

import javafx.scene.layout.Pane;
import millionaire.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

// GameContent class include the methods that is responsible to switch between game scenes.
class IntroScreen extends BorderPane{
    private static final IntroScreen instance = new IntroScreen();
    private static IntroScreen getInstance(){
        return instance;
    }

    private static OptionButton start;
    private IntroScreen(){
        super();
        ImageView logo =new ImageView();
        logo.setFitWidth(500);
        logo.setFitHeight(500);
        setTop(logo);
        setAlignment(logo, Pos.TOP_CENTER);

        Timer setLogo = new Timer();
        setLogo.schedule(new TimerTask() {
            @Override
            public void run() {
                logo.setImage(new Image("img/logo1.gif"));
            }
        }, 150);
        Timer changeLogo = new Timer();
        changeLogo.schedule(new TimerTask() {
            @Override
            public void run() {
                logo.setImage(new Image("img/logo2.gif"));
            }
        }, 4500);

// Create and add a start button with its style and action commands when it will be clicked
        start = new OptionButton();
        start.setText("New Game");
        setBottom(start);
        setAlignment(start, Pos.BOTTOM_CENTER);
        setPadding(new Insets(25, 0, 50, 0));
        setPrefSize(1024, 768);
    }

//

///////////////////////////////////////////////////////////////////////
//  addTo() will return a scene that contain game intro.
static void addTo(Pane pane){
    pane.getChildren().add(getInstance());
    start.setOnMouseClicked(event -> {
        PlayerNameScreen.addTo(pane);
    });
}

}
