package Millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;


public class LifeLineHintArea extends HBox {
    public static LifeLineHintArea lifeLineHintArea;
    public Label label0, label1, label2, label3;

    private StackPane sPane0, sPane1, sPane2, sPane3;

    public LifeLineHintArea(){
        super();
        label0 = new Label("Hrj");

        label0.setFont(Font.font("arial",30));

        sPane0 = new StackPane(label0);
        getChildren().addAll(sPane0);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(5));
    }
    public void changeText(String text) {
        label0.setText(text);
    }


    public static LifeLineHintArea getInstance(){
        if(lifeLineHintArea == null)
            lifeLineHintArea=new LifeLineHintArea();
        return lifeLineHintArea;
    }
    //
}