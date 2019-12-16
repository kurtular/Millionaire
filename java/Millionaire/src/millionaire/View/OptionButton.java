package millionaire.View;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import millionaire.FINAL_GLOBAL_VARIABLES.SoundEffectName;

/**
 * @author Mohammad
 */
// OptionButton class will create the options buttons that will be shown underneath question text.
class OptionButton extends Label {
/////////////////////////////////////////////////////////////////////
// Constructor
OptionButton(){
        super();
        setPrefSize(459,77);
// Create a svg that will be like a clip for the button (to avoid having empty spaces around the button (like a mask))
        SVGPath shape = new SVGPath();
        shape.setContent("M416.54,75.65H41.33c-11.4,0-21.94-5.3-26.83-13.51L0,37.83.51,37l14-23.45C19.39,5.3,29.93,0,41.33,0H416.54c11.4,0,21.94,5.3,26.83,13.51l14.5,24.31-14.5,24.32C438.48,70.35,427.94,75.65,416.54,75.65Z");
        setClip(shape);
// Set font style that will be shown inside the button, setting a specific mouse cursor and canceling the default focus .
        setWrapText(true);
        setFont(Font.font("arial",20));
        setTextAlignment(TextAlignment.CENTER);
        setFocusTraversable(false);
        setCursor(Cursor.HAND);
    }

///////////////////////////////////////////////////////////////////////////////////
// The member methods.
// setState() will change the style (state) of an option button depending on the possible state value (see the static variables at the start of the class). (between -1 and 1)
void setState(int state){
        switch (state){
            case -1:
                setId("wrongAnswer");
                SoundEffectPlayer.play(SoundEffectName.WRONG_ANSWER);
                break;
            case 0:
                setId("checkingAnswer");
                SoundEffectPlayer.play(SoundEffectName.CHECKING_ANSWER);
            break;
            case 1:
                setId("rightAnswer");
                SoundEffectPlayer.play(SoundEffectName.CORRECT_ANSWER);
            break;
            case 2:
                idProperty().setValue("");
                SoundEffectPlayer.stop();
            break;
            default: System.out.println("!!Please use possible state!!");
        }
    }
}
