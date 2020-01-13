package millionaire;

import javafx.application.Platform;

import java.util.TimerTask;

/**
 * It represents timer and it is just an abstract class so it is like utility.<br>
 * It kan be used wherever in the application.
 *
 * @author Mohammad.
 */
public abstract class Timer {
    /**
     * It runs a specific function depending on toDo value after some seconds depending on afterSeconds value.
     *
     * @param toDo         refers to the functionality that will run then.
     * @param afterSeconds refers to when the passed function or code will run.
     */
    public static void delay(Runnable toDo, double afterSeconds) {
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(toDo);
            }
        }, (long) (afterSeconds * 1000));
    }
}
