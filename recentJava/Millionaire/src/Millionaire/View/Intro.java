package Millionaire.View;

import javafx.scene.Group;
import javafx.scene.control.Button;

public class Intro  extends Group {

    public void getContent(){
        Button btn=new Button("hi");
        getChildren().add(btn);
    }

}
