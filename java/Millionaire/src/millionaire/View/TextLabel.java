package millionaire.View;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * It represnts text labels that will be shown underneath the non interactive texts like question text and balance text.
 *
 * @author Mohammad.
 */
class TextLabel extends Label {
    ////////////////////////////////////////////////////////////
    //              >>>>Class constructor.<<<<

    /**
     * It is a constructor method creates a TextLabel object.
     *
     * @param width  refers to TextLabel width.
     * @param height refers to TextLabel height.
     */
    TextLabel(int width, int height) {
        // Calling Label constructor.
        super();
        // Setting an image as a background for this object.
        ImageView img = new ImageView("img/default.png");
        img.setFitWidth(width);
        img.setFitHeight(height);
        this.setGraphic(img);

        // Setting the style of the text that will be shown inside this object.
        this.setWrapText(true);
        this.setTextFill(Color.valueOf("#fff"));
        this.setFont(Font.font("arial", 20));
        this.setContentDisplay(ContentDisplay.CENTER);
        this.setFocusTraversable(false);
        setMaxSize(width, height);
    }
}
