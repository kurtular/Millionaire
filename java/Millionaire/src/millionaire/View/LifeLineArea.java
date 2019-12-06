package millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import millionaire.Controller;

// LifeLineArea class will create the the images of the lifelines and the result of the lifelines.
public class LifeLineArea extends VBox {
    private static LifeLineArea lifeLineArea = new LifeLineArea();
    public LifeLine audience,friend,half,change;
    private Label lifeLineHint;

// The constructor
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

    // Creating actionevents if click on the images.

    private void setActions(){
        audience.setOnMouseClicked(event-> {
                disableActions(1);
                Controller.getInstance().useLifeLine("askThePeople");});
        friend.setOnMouseClicked(event-> {
                disableActions(2);
                Controller.getInstance().useLifeLine("callAFriend");});
        half.setOnMouseClicked(event-> {
                disableActions(3);
                Controller.getInstance().useLifeLine("removeHalf");});
        change.setOnMouseClicked(event-> {
                 disableActions(4);
                Controller.getInstance().useLifeLine("changeQuestion");});
    }

    // A method to change the image to the one with the red X(used) and turn off the actionevent.
    public void disableActions(int x){
        switch (x) {
            case 1: {
                audience.switchToUsedImage();
                audience.setOnMouseClicked(null);
                break;
            }
            case 2: {
                friend.switchToUsedImage();
                friend.setOnMouseClicked(null);
                break;
            }
            case 3: {
                half.switchToUsedImage();
                half.setOnMouseClicked(null);
                break;
            }
            case 4: {
                change.switchToUsedImage();
                change.setOnMouseClicked(null);
                break;
            }

        }
    }

    // A method to return the object because lifeLineArea object is private.
    protected static LifeLineArea getInstance(){
        return lifeLineArea;
    }
    // A method to set the text to screen showing friends answer.
    public void setLifeLineHint(String lifeLineHint) {
        this.lifeLineHint.setText(lifeLineHint);
    }
}




