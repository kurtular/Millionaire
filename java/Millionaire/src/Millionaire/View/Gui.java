package Millionaire.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Gui  extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button("Hello Millionare team!");
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout,300,300);
        stage.setScene(scene);
        stage.show();
    }
    public static void launchGui(String[] args){
        launch(args);
    }
}
