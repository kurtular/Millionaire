package Millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// GameContent class include the methods that is responsible to switch between game scenes.
public class GameContent{

///////////////////////////////////////////////////////////////////////
//  setIntroScene() will set the first scene of the game (intro).
static  Scene  setIntroScene(Stage stage){

// Create the main layout that will contain the other components.
    BorderPane container =new BorderPane();

// Create and add a start button with its style and action commands when it will be clicked
    OptionButton start = new OptionButton();
    start.setText("Start");
    start.setOnMouseClicked(event -> { setPlayScene(stage);});
    container.setBottom(start);
    container.setAlignment(start, Pos.BOTTOM_CENTER);
    container.setPadding(new Insets(0, 0, 50, 0));

// create the scene object that will be return (to Gui class) and linking it with css file.
    Scene scene = new Scene(container);
    scene.getStylesheets().add("style.css");
    return scene;
}

///////////////////////////////////////////////////////////////////////
//  setPlayScene() will set the scene that will be showing while the player is playing (match scene that show the questions).
    static void setPlayScene(Stage stage){


// create the scene object that will be return (to Gui class) and linking it with css file.
        Scene scene = new Scene(PlayScene.getInstance());
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
    }
}
