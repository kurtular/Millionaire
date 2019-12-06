package millionaire;
import javafx.application.Platform;
import java.util.TimerTask;

public abstract class Timer {
    public static void delay(Runnable toDo,double afterSeconds){
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(toDo);
            }
        }, (long)(afterSeconds*1000));
    }
}
