package millionaire.View;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import millionaire.Timer;

public class TimerLabel extends Label {
    private static final TimerLabel instance = new TimerLabel();
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
        this.setFont(Font.font("arial",20));
        this.setContentDisplay(ContentDisplay.CENTER);
        this.setFocusTraversable(false);
    }
    protected static void startTimer(){
        instance.setText(Integer.toString(Gui.shownSecondsInTimerLabel));
        Timer.delay(()->{
            if(Gui.shownSecondsInTimerLabel>0 && !Gui.stop){
                Gui.shownSecondsInTimerLabel--;
                startTimer();
            }
        }, 1);
    }
    static TimerLabel getInstance(){
        return instance;
    }
}
