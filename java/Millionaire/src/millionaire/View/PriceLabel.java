package millionaire.View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import millionaire.FINAL_GLOBAL_VARIABLES;

/**
 * PriceLabel is a HBox and it have three private class variables (BalanceLabel,numberLabel and a circle) and two final static boolean variables.
 * @author Jesse, Mohammad, Joakim
 * @version 1.0
 */
class PriceLabel extends HBox {
    final static boolean SAFE_LEVEL_PRICE_LABEL = true;
    final static boolean NORMAL_PRICE_LABEL = false;
    private Label balanceLabel;
    private Label numberLabel;
    private Circle circle;

    /**
     * Constructor will need three arguments (number, balance and a boolean type to decide the safe level or not) to create an object from PriceLabel.
     * @param number The number will decide which level players att
     * @param balance is the prize from 0 to 1 000 000 dollars. The balance will get value from FINAL-GLOBAL-VARIABLES
     * @param type will decide where is the safe level.
     */
    PriceLabel(String number,String balance,boolean type){
        super();
        circle = new Circle();
        circle.setFill(Color.WHITE);
        circle.setRadius(3.5f);
        circle.setVisible(false);
        BorderPane pane = new BorderPane(circle);
        pane.setCenter(circle);
        pane.setPadding(new Insets(0, 10, 0, 5));

        balanceLabel = new Label(FINAL_GLOBAL_VARIABLES.getCurrencySymbol()+balance);
        balanceLabel.setFont(Font.font(15));

        numberLabel = new Label(number);
        numberLabel.setFont(Font.font(15));
        numberLabel.setMinWidth(20);
        numberLabel.setMaxWidth(20);

        if(!type) {
            balanceLabel.setTextFill(Color.WHITE);
            numberLabel.setTextFill(Color.WHITE);
        }else {
            balanceLabel.setTextFill(Color.valueOf("#00d6a0"));
            numberLabel.setTextFill(Color.valueOf("#00d6a0"));
        }

        setPadding(new Insets(0,10,0,10));
        getChildren().addAll(numberLabel,pane,balanceLabel);
    }

    /**
     * This method will show the visibility of the circle.
     */
    void showCircle(){
        circle.setVisible(true);
    }

    /**
     * This method will disable the visibility of the circle .
     */
    void hideCircle() { circle.setVisible(false);}
}

