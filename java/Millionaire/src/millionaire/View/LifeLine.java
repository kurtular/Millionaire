package millionaire.View;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Ellipse;
import millionaire.Controller;
import millionaire.Timer;

/**
 * @author Henrik, Mohammad, Joakim
 * A class for creating the different lifelines and their view properties.
 */
class LifeLine extends Label {

    private boolean used;
    private ImageView img;
    private String type;
    private String soundEffectName;

    /**
     * The constructor for creating the label with the images.
     * @param LifeLineType String including which lifeline to be created.
     * @param SoundEffectName String including which soundeffectname the lifelineobject will earn.
     */
    LifeLine(String LifeLineType,String SoundEffectName) {
        this.type = LifeLineType;
        soundEffectName = SoundEffectName;
        img = new ImageView("img/"+type+".gif");
        img.setFitWidth(150);
        img.setFitHeight(100);
        setClip(new Ellipse(75, 50,75,50));
        setGraphic(img);
        setCursor(Cursor.HAND);
        used = false;
        setAction();
    }

    /**
     * To switch image to the one with a red cross when used the lifeline.
     */
    private void switchToUsedImage(){ img.setImage(new Image("img/"+type+"-used.png")); }

    /**
     * Creating actionevent when clicking at the lifeline to disable all other options, play a sound and activate the lifeline.
     */
    void setAction() {
        setOnMouseClicked(event -> {
            QuestionArea.getInstance().disableActions();
            LifeLineArea.getInstance().deactivateTemporarily();
            PlayScreen.getInstance().disableWithdrawing();
            disableAction();
            SoundEffectPlayer.play(soundEffectName);
            TimerLabel.getInstance().stopTimer();
            Timer.delay(() -> Controller.getInstance().useLifeLine(type), 4);
        });
    }

    /**
     * Disable the label and calling the other methods that will switch the image and set it as used.
     */
    private void disableAction(){
        switchToUsedImage();
        setDisable(true);
        setOpacity(0.8);
        setOnMouseClicked(null);
        setAsUsed();
    }
    //Joakim
    void reset(){
        img.setImage(new Image("img/"+type+".gif"));
        setAction();
        used=false;
        setDisable(false);
        setOpacity(1);
    }

    /**
     * Setting the lifeline as used.
     */
    private void setAsUsed(){used=true;}

    /**
     *
     * @return True or false depending if the lifeline is used or not
     */
    boolean isUsed(){return used;}
}
