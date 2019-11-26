package Millionaire.View;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static Millionaire.View.PlayScreen.playScreen;

// The main class of the View packet and it's represent the View class of the MVC design pattern.
public class Gui extends Application {

// start() will be run directly after calling launch(args) that is inside launchGui(). It's an overridden method after extending Application class. (javaFx).
    @Override
    public void start(Stage stage) {
// set the first scene that will be shown at the start of the game (game intro).
        stage.setScene(IntroScreen.addTo(stage));

// set some properties values for the stage object like a title ,frame icon and game frame dimensions(min).
        stage.setTitle("Miljonär");
        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/logo.png"));
        stage.show();
    }

// setEventsListner() passes a definition of GuiListners methods to QuestionArea class. It calls from controller.

    public void setEventsListner(EventsListner eventsListner) {
        IntroScreen.setListner(eventsListner);
        PlayScreen.setListner(eventsListner);
        LifeLineArea.setLifeLineAreaListner(eventsListner);
    }

// to launch the gui of the game.
    public void launchGui(String[] args){
        launch(args);
    }

// updateQuestion() method pass the question that will be shown to the PlayContent class (window will be shown during the match)
   public static void updateQuestion(String question,String option1,String option2,String option3,String option4,int balance){
    PlayScreen.updateQuestion(question, option1, option2, option3, option4, balance);
    }
    public void changeLabelsPAF(String label) {
        playScreen.changeTextPAF(label);
    }
}
