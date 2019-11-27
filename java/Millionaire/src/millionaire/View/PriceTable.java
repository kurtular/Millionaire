package millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class PriceTable extends BorderPane {
    private static PriceTable priceListArea;
    private PriceLabel priceLabel;
    private VBox vBox;

    public PriceTable() {
        priceLabel = new PriceLabel();
        vBox = new VBox(priceLabel.hBox[14], priceLabel.hBox[13], priceLabel.hBox[12], priceLabel.hBox[11], priceLabel.hBox[10], priceLabel.hBox[9], priceLabel.hBox[8], priceLabel.hBox[7], priceLabel.hBox[6], priceLabel.hBox[5], priceLabel.hBox[4], priceLabel.hBox[3], priceLabel.hBox[2], priceLabel.hBox[1], priceLabel.hBox[0]);
        vBox.setId("vBox");
        vBox.setSpacing(5);
        setCircleInvisible();
        currentPlace(6);
        this.setRight(vBox);
    }

    protected static PriceTable getInstance() {
        if (priceListArea == null)
            priceListArea = new PriceTable();
        return priceListArea;
    }


    private void setActions() {
    }

    private void setCircleInvisible() {
        for (int i = 0; i < 15; i++) {
            priceLabel.circle[i].setVisible(false);
        }
    }

    private void currentPlace(int position) {
        String style = "-fx-background-color: orange;";
        priceLabel.hBox[position - 1].setStyle(style);
        priceLabel.circle[position - 1].setFill(Color.WHITE);
        for (int i = 0; i < position; i++) {
            priceLabel.circle[i].setVisible(true);
        }
    }

    public void setCurrentPlace(byte parseByte) {
    }

    public String getCurrentBalance(byte parseByte) {
        return " ";
    }
}
