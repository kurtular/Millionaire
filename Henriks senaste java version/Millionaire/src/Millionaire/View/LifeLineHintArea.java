package Millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class LifeLineHintArea extends VBox {
    public static LifeLineHintArea lifeLineHintArea;
    public Label label0, label1;

    public LifeLineHintArea(){
        super();
        label1 = new Label(" ");
        label0 = new Label(" ");
        label0.setFont(Font.font("arial",30));
        label1.setFont(Font.font("arial",30));
        getChildren().addAll(label1,label0);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5));
    }
    public static LifeLineHintArea getInstance(){
        if(lifeLineHintArea == null)
            lifeLineHintArea=new LifeLineHintArea();
        return lifeLineHintArea;
    }
    //
}