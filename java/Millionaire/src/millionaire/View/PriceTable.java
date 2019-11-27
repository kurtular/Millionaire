package millionaire.View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class PriceTable extends BorderPane {
    private static PriceTable priceListArea;
    private int amount = 15;
    private Label[] textLabelList;
    private Label[] numLabelList;
    private String[] listStep = {" 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", "10", "11", "12", "13", "14", "15"};
    private final String[] priceList = {"100", "200", "300", "500", "1 000", "2 000", "4 000", "8 000", "16 000", "32 000", "64 000", "125 000", "250 000", "500 000", "1 000 000"};
    private Circle[] circle;
    HBox [] hBox = new HBox[15];

    public PriceTable() {
        super();
        circle = new Circle[amount];
        textLabelList = new Label[amount];
        numLabelList = new Label[amount];
        for (int i = 0; i < amount; i++) {
            textLabelList[i] = new Label();
            numLabelList[i] = new Label();
            circle[i] = new Circle();
            circle[i].setFill(Color.ORANGE);
            circle[i].setRadius(5.0f);
            circle[i].setTranslateY(5);
            circle[i]. setTranslateX(-5);
            textLabelList[i].setFont(Font.font(15));
            textLabelList[i].setTextFill(Color.ORANGE);
            numLabelList[i].setFont(Font.font(15));
            numLabelList[i].setTextFill(Color.ORANGE);
            numLabelList[i].setPadding(new Insets(0,10,0,10));
            if (i == 4 || i == 9 || i == 14) {
                textLabelList[i].setTextFill(Color.WHITE);
                numLabelList[i].setTextFill(Color.WHITE);
            }
        }
        for(int i= 0; i <amount; i++){
            textLabelList[i].setText("$"+priceList[i]);
            numLabelList[i].setText(listStep[i]);
            hBox[i] = new HBox(numLabelList[i],circle[i],textLabelList[i]);
        }

        VBox vBox = new VBox(hBox[14],hBox[13],hBox[12],hBox[11],hBox[10],hBox[9],hBox[8],hBox[7],hBox[6],hBox[5],hBox[4],hBox[3],hBox[2],hBox[1],hBox[0]);
        vBox.setPadding(new Insets(0));
        vBox.setSpacing(5);
        vBox.setId("vBox");
        this.setRight(vBox);
        setCircleInvisible();
        this.setActions();
        //setCurrentPlace(5);
    }

    protected static PriceTable getInstance() {
        if (priceListArea == null)
            priceListArea = new PriceTable();
        return priceListArea;
    }

    private void setActions() {
    }
    private void setCircleInvisible() {
        for (int i = 0; i < amount; i++) {
            circle[i].setVisible(false);
        }
    }
    void setCurrentPlace(int position) {
        for (int i = 0; i < position; i++) {
            circle[position-1].setVisible(true);
            String style = "-fx-background-color: rgba(255, 255, 255, 255);";
            hBox[i].setStyle(style);
            if (i == 4 || i == 9 || i == 14) {
                style = "-fx-background-color: rgba(255,153,0,1);";
                hBox[i].setStyle(style);
                circle[i].setFill(Color.WHITE);
            }
        }
    }
    String getCurrentBalance(byte position){
        return priceList[position-1];
    }
}
