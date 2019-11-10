package Millionaire.View;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class OptionButton extends Label {
    public static final int Checking = 0;
    public static final int Right = 1;
    public static final int Wrong = -1;

//
    protected OptionButton(){
        super();
        this.setPrefSize(459,77);
        SVGPath shape = new SVGPath();
        shape.setContent("M416.54,75.65H41.33c-11.4,0-21.94-5.3-26.83-13.51L0,37.83.51,37l14-23.45C19.39,5.3,29.93,0,41.33,0H416.54c11.4,0,21.94,5.3,26.83,13.51l14.5,24.31-14.5,24.32C438.48,70.35,427.94,75.65,416.54,75.65Z");
        this.setClip(shape);

        this.setWrapText(true);
        this.setFont(Font.font("arial",20));
        this.setTextAlignment(TextAlignment.CENTER);
        this.setFocusTraversable(false);
        this.setCursor(Cursor.HAND);
    }
//
    public void setState(int state){
        if(state==0)
            this.setId("checkingAnswer");
        else if(state==-1)
            this.setId("wrongAnswer");
        else if (state==1)
            this.setId("rightAnswer");
        else System.out.println("!!Please use possible state!!");
    }
//

}
