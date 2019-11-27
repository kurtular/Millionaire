package millionaire.View;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.awt.*;

public class PriceLabel extends HBox {
    public Label[] textLabelList;
    public Label[] numLabelList;
    public String[] listStep = {" 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", "10", "11", "12", "13", "14", "15"};
    public final String[] priceList = {"$100", "$200", "$300", "$500", "$1 000", "$2 000", "$4 000", "$8 000", "$16 000", "$32 000", "$64 000", "$125 000", "$250 000", "$500 000", "$1 000 000"};
    public Circle[] circle;
    HBox [] hBox = new HBox[15];

    public PriceLabel(){
        super();
        circle = new Circle[15];
        textLabelList = new Label[15];
        numLabelList = new Label[15];
        for (int i = 0; i < 15; i++) {
            textLabelList[i] = new Label();
            numLabelList[i] = new Label();
            circle[i] = new Circle();
            circle[i].setFill(Color.WHITE);
            circle[i].setRadius(3.5f);
            circle[i].setTranslateY(7);
            circle[i]. setTranslateX(-7);
            textLabelList[i].setFont(javafx.scene.text.Font.font(15));
            textLabelList[i].setTextFill(Color.WHITE);
            numLabelList[i].setFont(Font.font(15));
            numLabelList[i].setTextFill(Color.WHITE);
            numLabelList[i].setPadding(new javafx.geometry.Insets(0,10,0,10));
            if (i == 4 || i == 9 || i == 14) {
                textLabelList[i].setId("textLabel");
                numLabelList[i].setId("textLabel");
            }
        }
        for(int i= 0; i <15; i++){
            textLabelList[i].setText(priceList[i]);
            numLabelList[i].setText(listStep[i]);
            hBox[i] = new HBox(numLabelList[i],circle[i],textLabelList[i]);
        }
    }
}

