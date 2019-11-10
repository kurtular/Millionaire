package Millionaire.View;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Gui extends Application {
    private GuiListners eventsListner;

    public void start(Stage stage) {
        BorderPane playWindow =new BorderPane();
        BorderPane questionArea = QuestionArea.createQuestionArea();
        playWindow.setBottom(questionArea);
        Scene scene = new Scene(playWindow);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.setTitle("Millionaire");
        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.setMaxWidth(1024);
        stage.setMaxHeight(768);
        stage.show();
    }

    public void setEventsListner(GuiListners eventsListner) {
        this.eventsListner = eventsListner;
        QuestionArea.setQuestionAreaListner(eventsListner);
    }

    public void launchGui(String[] args){
        launch(args);
    }
}
