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
    public SoundEffects rightAnswer;

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
        this.setActions(1,2,3,4);
    }

    // Creating actionevents if click on the images.

    private void setActions(int...integers){
        for (int i : integers) {
            if (i==1) {
                audience.setOnMouseClicked(event -> {
                    disableActions(1);
                    Controller.getInstance().callTheHintMethods("askThePeople");
                });
            }
            if (i==2) {
                friend.setOnMouseClicked(event -> {
                    disableActions(2);
                    Controller.getInstance().callTheHintMethods("callAFriend");
                });
            }
            if (i==3) {
                half.setOnMouseClicked(event -> {
                    disableActions(3);
                    Controller.getInstance().callTheHintMethods("removeHalf");
                });
            }
            if (i==4) {
                change.setOnMouseClicked(event -> {
                    disableActions(4);
                    Controller.getInstance().callTheHintMethods("changeQuestion");
                });
            }
        }
    }

    // A method to change the image to the one with the red X(used) and turn off the actionevent and make it used.
    public void disableActions(int x){
        switch (x) {
            case 1: {
                audience.switchToUsedImage();
                audience.setOnMouseClicked(null);
                audience.used = true;
                break;
            }
            case 2: {
                rightAnswer.play();
                friend.switchToUsedImage();
                friend.setOnMouseClicked(null);
                friend.used = true;
                break;
            }
            case 3: {
                half.switchToUsedImage();
                half.setOnMouseClicked(null);
                half.used = true;
                break;
            }
            case 4: {
                change.switchToUsedImage();
                change.setOnMouseClicked(null);
                change.used = true;
                break;
            }

        }
    }

    //A method to temporarily unused disable lifelines after answered a question.

    public void tempDisableLifeLinesAfterAnswer(){
        if (!audience.used ) {
            audience.setOnMouseClicked(null);
            audience.tempDisabled = true;
        }
        if (!friend.used ) {
            friend.setOnMouseClicked(null);
            friend.tempDisabled = true;
        }
        if (!half.used ) {
            half.setOnMouseClicked(null);
            half.tempDisabled = true;
        }
        if (!change.used ) {
            change.setOnMouseClicked(null);
            change.tempDisabled = true;
        }
    }

    // A method to enable the temporarily disabled lifelines.

    protected void enableTempDisabledLifeLines() {
        if (LifeLineArea.getInstance().audience.tempDisabled) {
            LifeLineArea.getInstance().setActions(1);
        }
        if (LifeLineArea.getInstance().friend.tempDisabled) {
            LifeLineArea.getInstance().setActions(2);
        }
        if (LifeLineArea.getInstance().half.tempDisabled) {
            LifeLineArea.getInstance().setActions(3);
        }
        if (LifeLineArea.getInstance().change.tempDisabled) {
            LifeLineArea.getInstance().setActions(4);
        }

    }

    // A method to return the object because lifeLineArea object is private.
    public static LifeLineArea getInstance(){
        return lifeLineArea;
    }
    // A method to set the text to screen showing friends answer.
    public void setLifeLineHint(String lifeLineHint) {
        this.lifeLineHint.setText(lifeLineHint);
    }
}




