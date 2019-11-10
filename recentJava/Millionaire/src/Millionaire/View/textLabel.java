package Millionaire.View;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

class textLabel extends Label {

    protected textLabel(int width,int height){
        super();
        ImageView img = new ImageView("img/default.png");
        img.setFitWidth(width);
        img.setFitHeight(height);
        this.setGraphic(img);
        this.setWrapText(true);
        this.setTextFill(Color.valueOf("#fff"));
        this.setFont(Font.font("arial",20));
        this.setContentDisplay(ContentDisplay.CENTER);
        this.setFocusTraversable(false);
    }
}
