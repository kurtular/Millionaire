package millionaire.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// The main class of the View packet and it's represent the View class of the MVC design pattern.
public class Gui extends Application {
    Pane content;
// start() will be run after calling launch(args) that is inside launchGui(). It's an overridden method after extending Application class. (javaFx).
    @Override
    public void start(Stage stage) {
        content=new Pane();
// set the game intro (IntroScreen) and add it to .
        IntroScreen.addTo(content);
        Scene scene = new Scene(content);
        stage.setScene(scene);

// set some properties values for the stage object like a title ,frame icon and game frame dimensions(min).
        stage.setTitle("Miljonär");
        scene.getStylesheets().add("style.css");
        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/logo.png"));
        stage.show();
    }

// to launch the gui of the game.
    public void launchGui(String[] args){
        launch(args);
    }

// updateQuestion() method pass the question that will be shown to the PlayContent class (window will be shown during the match)
   public void updateQuestion(String question,String option1,String option2,String option3,String option4,String currentQuestion){
        PriceTable.getInstance().setCurrentPlace(Byte.parseByte(currentQuestion));
        String balance = PriceTable.getInstance().getCurrentBalance( Byte.parseByte(currentQuestion));
        QuestionArea.getInstance().updateQuestion(question, option1, option2, option3, option4,balance);
    }
//
    public void setOptionButtonState(char optionButtonSymbol,int optionButtonState){
        QuestionArea.getInstance().setOptionButtonState(optionButtonSymbol,optionButtonState);
    }
//
    public void enableActions(){
        QuestionArea.getInstance().enableActions();
    }
//
    public void disableActions(){
        QuestionArea.getInstance().disableActions();
    }
//
    public void setLifeLineHint(String hint) { LifeLineArea.getInstance().setLifeLineHint(hint); }

}