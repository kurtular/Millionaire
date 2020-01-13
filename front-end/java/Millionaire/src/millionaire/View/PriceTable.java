package millionaire.View;

import javafx.scene.layout.VBox;
import millionaire.FINAL_GLOBAL_VARIABLES;

/**
 * PriceTable is a VBox. This class only allowed to create a single instance from it because of the private constructor (singleton).
 * To get an instance of this class, you need to call getInstance() method. getInstance() method has no modifier and it's static.
 * PriceTable class contains a private static class variables and a private class variable.
 * @author Jesse, Mohammad, Joakim
 * @version 1.0
 */
class PriceTable extends VBox {
    //
    private static final PriceTable instance = new PriceTable();

    static PriceTable getInstance() {
        return instance;
    }

    //
    private final PriceLabel[] priceLabels;

    /**
     * Constructor will create a list of prizes and priceLabel to an object of PriceTable.
     * the first for-loop decide where is the safe and not safe level.
     * the second for-loop below will add all priceLabels to PriceTable's object.
     */
    private PriceTable() {
        super();
//
        final String[] priceList = FINAL_GLOBAL_VARIABLES.getPRIZES();
        priceLabels = new PriceLabel[priceList.length];

        for (byte i = 1; i < priceList.length; i++) {
            if (i % 5 != 0) {
                priceLabels[i] = new PriceLabel(Byte.toString(i), priceList[i], PriceLabel.NORMAL_PRICE_LABEL);

            } else {
                priceLabels[i] = new PriceLabel(Byte.toString(i), priceList[i], PriceLabel.SAFE_LEVEL_PRICE_LABEL);
            }
        }

        for (byte i = (byte) (priceList.length - 1); i > 0; i--) {
            getChildren().add(priceLabels[i]);
        }

        setId("PriceTable");
        setSpacing(5);
        setMaxHeight(320);
    }

    /**
     * This method will set the circle to visible at the current balance players have and will show the orange background(PriceLabel(level and balance)) at a current game's level.
     * Otherwise there will be no circle shown and the background will be set to default.
     * @param position tell which level players are currently at.
     */
    void setCurrentPlace(int position) {
        if (position>1)
            priceLabels[position-1].showCircle();

        priceLabels[position].setStyle("-fx-background-color: orange;");

        if (position > 1) {
            priceLabels[position - 1].setStyle("");
        }
    }

    /**
     * This method will reset the PriceTable to its default.
     */
    void resetPriceTable() {
        for (int i = 1; i < priceLabels.length; i++) {
            priceLabels[i].hideCircle();
            priceLabels[i].setStyle("");
        }

    }
}
