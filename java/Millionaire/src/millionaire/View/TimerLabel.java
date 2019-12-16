package millionaire.View;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import millionaire.FINAL_GLOBAL_VARIABLES;
import millionaire.Timer;

/**
 * @author Mohammad, Millad
 */
public class TimerLabel extends Label {
    private static final TimerLabel instance = new TimerLabel();
    static TimerLabel getInstance(){
        return instance;
    }

    private boolean isStopped;
    private byte shownSeconds;
    private TimerLabel(){
        super();
        Circle shape =new Circle();
        shape.setRadius(110 / 2f);
        shape.setCenterX(110 / 2f);
        shape.setCenterY(110 / 2f);
        this.setClip(shape);
        ImageView img = new ImageView("img/timer.png");
        img.setFitWidth(110);
        img.setFitHeight(110);
        this.setGraphic(img);
        this.setWrapText(true);
        this.setTextFill(Color.valueOf("#fff"));
        this.setFont(Font.font("arial",30));
        this.setContentDisplay(ContentDisplay.CENTER);
        this.setFocusTraversable(false);
    }
    void startTimer(){
        isStopped=false;
        setText(Integer.toString(shownSeconds));
        setSecondsColor();
        Timer.delay(()->{
            if(shownSeconds > 0 && !isStopped){
                shownSeconds--;
                startTimer();
            }
        }, 1);
    }
    void resetTimer(){
        stopTimer();
        shownSeconds = (byte)FINAL_GLOBAL_VARIABLES.getQuestionDuration();
    }
    void stopTimer(){
        isStopped = true;
    }
    public byte getShownSeconds() {
        return shownSeconds;
    }
    private void setSecondsColor(){
        if(shownSeconds<6){
            setTextFill(Paint.valueOf("#ff3434"));
        } else if(shownSeconds<16){
            setTextFill(Paint.valueOf("#faff6a"));
        }
        else {
            setTextFill(Paint.valueOf("#fff"));
        }
    }
}

