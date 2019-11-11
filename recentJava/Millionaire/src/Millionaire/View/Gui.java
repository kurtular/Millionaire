package Millionaire.View;

import javafx.application.Application;
import javafx.stage.Stage;

// The main class of the View packet and it's represent the View class of the MVC design pattern.
public class Gui extends Application {

// start() will be run directly after calling launch(args) that is inside launchGui(). It's an overridden method after extending Application class. (javaFx).
    @Override
    public void start(Stage stage) {
// set the first scene that will be shown at the start of the game (game intro).
        stage.setScene(GameContent.setIntroScene(stage));

// set some properties values for the stage object like a title , game frame dimensions (min,max) and its place.
        stage.setTitle("Millionaire");
        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.setResizable(false);
        stage.setX(1024/2);
        stage.setY(768/4);
        stage.show();
    }

///////////////////////////////////////////////////////////////////////
// setEventsListner() passes a definition of GuiListners methods to QuestionArea class. It calls from controller.
    public void setEventsListner(GuiListners eventsListner) {

        QuestionArea.setQuestionAreaListner(eventsListner);
    }

///////////////////////////////////////////////////////////////////////
// to launch the gui of the game.
    public void launchGui(String[] args){
        launch(args);
    }
}
