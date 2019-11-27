package millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import millionaire.Controller;


public class LifeLineArea extends VBox {
    private static LifeLineArea lifeLineArea = new LifeLineArea();
    public LifeLine audience,friend,half,change;
    private Label lifeLineHint;


    private LifeLineArea(){
        super();
        audience = new LifeLine(LifeLine.AUDIENCE);
        friend = new LifeLine(LifeLine.FRIEND);
        half = new LifeLine(LifeLine.HALF);
        change = new LifeLine(LifeLine.CHANGE);
        HBox lifeLines =new HBox(audience,friend,half,change);
        lifeLines.setSpacing(10);
        lifeLines.setAlignment(Pos.CENTER);

        lifeLineHint = new Label();
        lifeLineHint.setFont(Font.font("arial",30));
        lifeLineHint.setMaxWidth(500);
        lifeLineHint.setWrapText(true);
        lifeLineHint.setTextAlignment(TextAlignment.CENTER);
        lifeLineHint.setAlignment(Pos.CENTER);

        getChildren().addAll(lifeLines,lifeLineHint);
        setSpacing(30);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(5));
        this.setActions();
    }

    //

    private void setActions(){
        audience.setOnMouseClicked(event-> Controller.getInstance().setLifeLine("askThePeople"));
        friend.setOnMouseClicked(event->Controller.getInstance().setLifeLine("callAFriend"));
        half.setOnMouseClicked(event->Controller.getInstance().setLifeLine("removeHalf"));
        change.setOnMouseClicked(event->Controller.getInstance().setLifeLine("changeQuestion"));
    }

    //
    public void disableActions(int x){
        switch (x) {
            case 1: {
                audience.setOnMouseClicked(null);
            }
            case 2: {
                friend.disable();
                friend.setOnMouseClicked(null);
            }
            case 3: {
                half.setOnMouseClicked(null);
            }
            case 4: {
                change.setOnMouseClicked(null);
            }

        }
    }

//
    public static LifeLineArea getInstance(){ // todo make it protected
        return lifeLineArea;
    }
//
    public void setLifeLineHint(String lifeLineHint) {
        this.lifeLineHint.setText(lifeLineHint);
    }
}



