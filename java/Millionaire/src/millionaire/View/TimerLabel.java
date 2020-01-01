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
 * It represents TimerLabel there will be shown timer value (seconds).
 *
 * @author Mohammad, Millad.
 */
class TimerLabel extends Label {
    //           >>>>Class variables and methods.<<<<
    // The following variable and method created to apply singleton design pattern.
    /**
     * It is the only instance of this class (singleton).
     */
    private static final TimerLabel instance = new TimerLabel();

    /**
     * It returns TimerLabel object.
     *
     * @return the only possible instance of this class (singleton).
     */
    static TimerLabel getInstance() {
        return instance;
    }

    /////////////////////////////////////////////////////////////
    //              >>>>Member variables.<<<<
    private boolean isStopped;
    private byte shownSeconds;

    ////////////////////////////////////////////////////////////
    //              >>>>Class constructor.<<<<

    /**
     * It is a constructor method creates a TimerLabel object.
     */
    private TimerLabel() {
        // Calling Label constructor.
        super();

        // setup TimerLabel object
        Circle shape = new Circle();
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
        this.setFont(Font.font("arial", 30));
        this.setContentDisplay(ContentDisplay.CENTER);
        this.setFocusTraversable(false);
    }
    /////////////////////////////////////////////////////////////
    //                >>>> Member methods.<<<<

    /**
     * It starts the timer.
     */
    void startTimer() {
        isStopped = false;
        setText(Integer.toString(shownSeconds));
        // Set text color depending on seconds value.
        setSecondsColor();
        // decrease seconds with -1 after a second.
        Timer.delay(() -> {
            if (shownSeconds > 0 && !isStopped) {
                shownSeconds--;
                startTimer();
            }
        }, 1);
    }

    /**
     * It resets TimerLabel value (set its value to max value).
     */
    void resetTimer() {
        stopTimer();
        shownSeconds = (byte) FINAL_GLOBAL_VARIABLES.getQuestionDuration();
    }

    /**
     * It stops the timer.
     */
    void stopTimer() {
        isStopped = true;
    }

    /**
     * It returns the remaining seconds.
     *
     * @return a number that refer to the remaining seconds;
     */
    byte getShownSeconds() {
        return shownSeconds;
    }

    /**
     * It changes timer text color depending on its value.
     */
    private void setSecondsColor() {
        if (shownSeconds < 6) {
            setTextFill(Paint.valueOf("#ff3434"));
        } else if (shownSeconds < 16) {
            setTextFill(Paint.valueOf("#faff6a"));
        } else {
            setTextFill(Paint.valueOf("#fff"));
        }
    }
}

