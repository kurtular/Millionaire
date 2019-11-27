package millionaire.View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import millionaire.FINAL_GLOBAL_VARIABLES;


 class PriceLabel extends HBox {
    final static boolean SAFE_LEVEL_PRICE_LABEL = true;
    final static boolean NORMAL_PRICE_LABEL = false;
    private Label balanceLabel;
    private Label numberLabel;
    private Circle circle;

    PriceLabel(String number,String balance,boolean type){
        super();
        circle = new Circle();
        circle.setFill(Color.WHITE);
        circle.setRadius(3.5f);
        circle.setVisible(false);
        BorderPane pane = new BorderPane(circle);
        pane.setCenter(circle);
        pane.setPadding(new Insets(0, 10, 0, 5));

        balanceLabel = new Label(balance + FINAL_GLOBAL_VARIABLES.getCurrencySymbol());
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
            numberLabel.setTextFill(Color.WHITE);
        }

        setPadding(new Insets(0,10,0,10));
        getChildren().addAll(numberLabel,pane,balanceLabel);
    }
    void showCircle(){
        circle.setVisible(true);
    }
}

