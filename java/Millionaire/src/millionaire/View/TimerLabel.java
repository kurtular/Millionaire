package millionaire.View;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

class TimerLabel extends Label {
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
    protected void setTimerValue(int value){
        if(value==0)
            this.setText("0");
        else if(value>0 &&value<31)
            this.setText(Integer.toString(value)); //todo add to the actual seconds.
        else if(value == -1)
            this.setText(Integer.toString(Integer.parseInt(this.getText())-1));
        else System.out.println("!!Wrong data!!.\nCheck setTimer method location: View > QuestionArea >setTimer()");
    }
    static TimerLabel getInstance(){
        return instance;
    }
}
