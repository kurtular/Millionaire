package millionaire.View;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// TextLabel class will create the text labels that will be shown underneath the non interactive texts like question text and balance text.
class TextLabel extends Label {

//////////////////////////////////////////////////////////////////////////
// TextLabel constructor.

    TextLabel(int width, int height){
        super();
// Setting an image as a background for this object.
        ImageView img = new ImageView("img/default.png");
        img.setFitWidth(width);
        img.setFitHeight(height);
        this.setGraphic(img);
// Setting the style of the text that will be shown inside this object.
        this.setWrapText(true);
        this.setTextFill(Color.valueOf("#fff"));
        this.setFont(Font.font("arial",20));
        this.setContentDisplay(ContentDisplay.CENTER);
        this.setFocusTraversable(false);
    }
}
