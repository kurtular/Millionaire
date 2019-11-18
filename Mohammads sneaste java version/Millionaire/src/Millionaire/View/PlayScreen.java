package Millionaire.View;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PlayScreen extends BorderPane {
    private static EventsListner Listner;
    protected static PlayScreen playScreen;
    private PlayScreen(){
        super();
        this.setPrefSize(1024, 768);

// Create the question area (the contain the question text with it's option and the current balance that player have under the competition)
        setBottom(QuestionArea.getInstance());
//TODO bring the other parts

    }
//
    public static void setListner(EventsListner listner){
        Listner = listner;
        QuestionArea.setQuestionAreaListner(Listner);
    }
//
protected static PlayScreen getInstance(){
    if(playScreen == null)
        playScreen =new PlayScreen();
    return playScreen;
}
//
    public static void updateQuestion(String question,String option1,String option2,String option3,String option4,int balance){
        QuestionArea.setQuestionArea(question,option1,option2,option3,option4,balance);
    }
    ///////////////////////////////////////////////////////////////////////
//  addTo() will return the scene that will be shown while the player is playing (match scene that show the questions).
    protected static void addTo(Stage stage){

// create the scene object that will be return (to Gui class) and linking it with css file.
        Scene scene = new Scene(getInstance());
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
    }

}
