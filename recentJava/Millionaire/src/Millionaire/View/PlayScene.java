package Millionaire.View;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class PlayScene extends BorderPane {
    protected static PlayScene playScene = new PlayScene();
    private PlayScene(){
        super();
        this.setPrefSize(1024, 768);

// Create the question area (the contain the question text with it's option and the current balance that player have under the competition)
        setBottom(QuestionArea.getInstance());
//TODO bring the other parts

    }

//
    public static PlayScene getInstance(){
        return playScene;
    }

}
