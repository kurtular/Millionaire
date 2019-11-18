package Millionaire.View;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

class TimerLabel extends Label {

    protected TimerLabel(int width,int height){
        super();
        Circle shape =new Circle();
        shape.setRadius(width/2);
        shape.setCenterX(width/2);
        shape.setCenterY(height/2);
        this.setClip(shape);
        ImageView img = new ImageView("img/timer.png");
        img.setFitWidth(width);
        img.setFitHeight(height);
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
}
