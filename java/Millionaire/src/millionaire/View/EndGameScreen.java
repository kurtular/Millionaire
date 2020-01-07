package millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import millionaire.FINAL_GLOBAL_VARIABLES;
import millionaire.FINAL_GLOBAL_VARIABLES.SoundEffectName;

/**
 * EndGameScreen is a VBox. In EndGameScreen contains two private static class variables and 3 private class variables.
 * The following class is using singleton pattern which can only generate single object from this class.
 * The only way to create the object of this class is by calling getInstance() method. This method is public and static.
 * @author Jesse, Mohammad
 * @version 1.0
 */
class EndGameScreen extends VBox {
    private static final EndGameScreen instance = new EndGameScreen();
    private final Label playerName;
    private final Label balance;
    private final Label date;
    private static OptionButton home;

    /**
     * Constructor will create a player's name, balance and date label to EndGameScreen's object.
     * The EndGameScreen's object will also has a button. The child node in the object would get a specific position in VBox.
     */
    private EndGameScreen() {
        super();

        playerName = new Label();
        balance = new Label();
        date = new Label();


        playerName.setTranslateX(220);
        playerName.setTranslateY(45);
        playerName.setFont(Font.font(30));
        playerName.setMaxWidth(400);

        balance.setTranslateX(150);
        balance.setTranslateY(35);
        balance.setFont(Font.font(30));

        date.setTranslateX(110);
        date.setTranslateY(20);
        date.setFont(Font.font(20));

        home = new OptionButton();
        home.setText("HEM");
        home.setScaleX(0.7);
        home.setScaleY(0.7);
        home.setFont(Font.font(30));


        ImageView moneyCheck = new ImageView("img/moneycheck.png");
        moneyCheck.setFitHeight(400);
        moneyCheck.setFitWidth(900);

        VBox CheckData = new VBox(playerName, balance, date);
        CheckData.setAlignment(Pos.CENTER_LEFT);
        CheckData.setSpacing(60);

        StackPane pane = new StackPane();
        pane.getChildren().addAll(moneyCheck, CheckData);
        pane.setId("pane");

        VBox content = new VBox();
        content.getChildren().addAll(pane, home);
        content.setSpacing(70);
        content.setMaxSize(920, 600);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);
        content.setId("EndGameScreenContent");

        getChildren().add(content);
        setAlignment(Pos.CENTER);
        setPrefSize(1024, 768);
    }

    /**
     * This method take in three arguments and set the value to player's name, balance and date to the paycheck to assign player's name, balance and date for paycheck.
     * @param playerName Player's name
     * @param playerBalance player's balance
     * @param gameDate Date of player when the game ended
     */
    private void setCheckData(String playerName, String playerBalance, String gameDate) {
        this.playerName.setText(playerName);
        this.balance.setText(playerBalance + FINAL_GLOBAL_VARIABLES.getCurrencySymbol());
        this.date.setText(gameDate);
    }
    /**
     *  This method will firstly set the value of player's name, balance and date to the EndGameScreen's object after the game ends with the purpose later to update to database.
     *  After the information has assigned to the EndGameScreen's object. The EndGameScreen will be reset by using clear() to clear all the child nodes.
     *  The EndGameScreen's object add to pane with an unassigned player's name, balance and date. The sounds effect will be played.
     *  When player click on home button will caused an event and return to IntroScreen and reset the PriceTable, LifeLineArea and QuestionArea to it's default value.
     * @param playerName Player's name
     * @param playerBalance player's balance
     * @param gameDate Date of player when the game ended
     */
    static void show(String playerName, String playerBalance, String gameDate) {
        instance.setCheckData(playerName, playerBalance, gameDate);
        Gui.content.getChildren().clear();
        Gui.content.getChildren().add(instance);
        SoundEffectPlayer.play(SoundEffectName.GAME_END);
        home.setOnMouseClicked(event -> {
            IntroScreen.getInstance().show();
            Gui.reset();
        });
    }
}
