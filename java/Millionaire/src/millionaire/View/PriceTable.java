package millionaire.View;

import javafx.scene.layout.VBox;
import millionaire.FINAL_GLOBAL_VARIABLES;

class PriceTable extends VBox {
    //
    private static PriceTable instance = new PriceTable();

    static PriceTable getInstance() {
        return instance;
    }

    //
    private PriceLabel[] priceLabels;

    private PriceTable() {
        super();
//
        final String[] priceList = FINAL_GLOBAL_VARIABLES.getPRIZES();
        priceLabels = new PriceLabel[priceList.length];
//
        for (byte i = 1; i < priceList.length; i++) {
            if (i % 5 != 0) {
                priceLabels[i] = new PriceLabel(Byte.toString(i), priceList[i], PriceLabel.NORMAL_PRICE_LABEL);

            } else {
                priceLabels[i] = new PriceLabel(Byte.toString(i), priceList[i], PriceLabel.SAFE_LEVEL_PRICE_LABEL);
            }
        }
//
        for (byte i = (byte) (priceList.length - 1); i > 0; i--) {
            getChildren().add(priceLabels[i]);
        }

        setId("PriceTable");
        setSpacing(5);
        setMaxHeight(320);
    }

    void setCurrentPlace(int position) {
        if (position>1)
            priceLabels[position-1].showCircle();

        priceLabels[position].setStyle("-fx-background-color: orange;");

        if (position > 1) {
            priceLabels[position - 1].setStyle("");
        }
    }

    void resetPriceTable() {
        for (int i = 1; i < priceLabels.length; i++) {
            priceLabels[i].hideCircle();
            priceLabels[i].setStyle("");
        }

    }
}
