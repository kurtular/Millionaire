package millionaire.View;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import millionaire.FINAL_GLOBAL_VARIABLES.SoundEffectName;

/**
 * It represents options buttons. (shown underneath question text).
 *
 * @author Mohammad.
 */
class OptionButton extends Region {
    /**
     * It is The shown text inside the object.
     */
    private Label text;
    ////////////////////////////////////////////////////////////
    //              >>>>Class constructor.<<<<

    /**
     * It is a constructor method creates a OptionButton object that doesn't have a sign at left side.
     */
    OptionButton() {
        super();
        setMaxSize(459, 77);
        // Create a svg that will be like a clip for the button (to avoid having empty spaces around the button (like a mask)) and setting a specific mouse cursor and canceling the default focus.
        SVGPath shape = new SVGPath();
        shape.setContent("M416.54,75.65H41.33c-11.4,0-21.94-5.3-26.83-13.51L0,37.83.51,37l14-23.45C19.39,5.3,29.93,0,41.33,0H416.54c11.4,0,21.94,5.3,26.83,13.51l14.5,24.31-14.5,24.32C438.48,70.35,427.94,75.65,416.54,75.65Z");
        setClip(shape);
        setFocusTraversable(false);
        setCursor(Cursor.HAND);
        // Set text style that will be shown inside the button .
        text = new Label();
        text.setPrefSize(459, 77);
        text.setWrapText(true);
        text.setFont(Font.font("arial", 20));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setAlignment(Pos.CENTER);
        text.setId("OptionButton_text");
        // Add the text to the button
        getChildren().add(text);
    }
    /**
     * It is a constructor method creates a OptionButton object that have a sign at left side like Play screen's option buttons .
     */
    OptionButton(char sign) {
        super();
        setPrefSize(459, 77);

        // Create a svg that will be like a clip for the button (to avoid having empty spaces around the button (like a mask)) and setting a specific mouse cursor and canceling the default focus.
        SVGPath shape = new SVGPath();
        shape.setContent("M416.54,75.65H41.33c-11.4,0-21.94-5.3-26.83-13.51L0,37.83.51,37l14-23.45C19.39,5.3,29.93,0,41.33,0H416.54c11.4,0,21.94,5.3,26.83,13.51l14.5,24.31-14.5,24.32C438.48,70.35,427.94,75.65,416.54,75.65Z");
        setClip(shape);
        setFocusTraversable(false);
        setCursor(Cursor.HAND);

        // Set text style that will be shown inside the button.
        text = new Label();
        text.setPrefSize(370, 77);
        text.setMaxWidth(370);
        text.setWrapText(true);
        text.setFont(Font.font("arial", 20));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setAlignment(Pos.CENTER);
        text.setTranslateX(55);
        text.setId("OptionButton_text");

        // Setup the sign that will be shown in front of the text.
        Label symbol = new Label();
        symbol.setPrefHeight(77);
        symbol.setText(Character.toString(sign));
        symbol.setFont(Font.font("arial", 30));
        symbol.setTranslateX(30);
        symbol.setTextFill(Color.ORANGE);
        symbol.setTextAlignment(TextAlignment.CENTER);
        // Add the sign and the text to the button
        getChildren().addAll(symbol,text);
    }

    /////////////////////////////////////////////////////////////
    //                >>>> Member methods.<<<<

    /**
     * It sets a text inside text label of the option button object.
     * @param text is a string value that will be shown.
     */
    void setText(String text){
        this.text.setText(text);
    }
    /**
     * It sets the font of the text label of the option button object.
     * @param font is a Font value that will be use.
     */
    void setFont(Font font){
        this.text.setFont(font);
    }

    /**
     * It disables the option button.
     * @param value is a boolean value that will refer to the desired state.
     */
    void setAsDisabled(boolean value){
        setDisable(value);
        text.setOpacity(1);
    }
    /**
     * It changes the style (state) of an option button depending on the possible state value (see the static variables at the start of the class). (between -1 and 2)
     *
     * @param OptionButtonState refers to a state that an option button will have.
     */
    void setState(int OptionButtonState) {
        switch (OptionButtonState) {
            case -1:
                setId("wrongAnswer");
                SoundEffectPlayer.play(SoundEffectName.WRONG_ANSWER);
                text.setId("OptionButton_text");
                break;
            case 0:
                setId("checkingAnswer");
                SoundEffectPlayer.play(SoundEffectName.CHECKING_ANSWER);
                text.setId("checkingAnswer_text");
                break;
            case 1:
                setId("rightAnswer");
                SoundEffectPlayer.play(SoundEffectName.CORRECT_ANSWER);
                text.setId("OptionButton_text");
                break;
            case 2:                                                                                                     // The default state
                idProperty().setValue("");
                SoundEffectPlayer.stop();
                text.setId("OptionButton_text");
                break;
            default:
                System.out.println("!!Please use possible state!!");
        }
    }
}
