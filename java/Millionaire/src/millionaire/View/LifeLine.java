package millionaire.View;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Ellipse;
import millionaire.Controller;
import millionaire.Timer;

class LifeLine extends Label {

//
    private boolean used;
    private ImageView img;
    private String type;
    private String soundEffectName;

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

//
    private void switchToUsedImage(){ img.setImage(new Image("img/"+type+"-used.png")); }
    //
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
    //
    private void disableAction(){
        switchToUsedImage();
        setDisable(true);
        setOpacity(0.8);
        setOnMouseClicked(null);
        setAsUsed();
    }
    //
    void reset(){
        img.setImage(new Image("img/"+type+".gif"));
        setAction();
        used=false;
        setDisable(false);
        setOpacity(1);
    }
    //
    private void setAsUsed(){used=true;}
    //
    boolean isUsed(){return used;}
}
