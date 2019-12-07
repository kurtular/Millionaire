package millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import millionaire.FINAL_GLOBAL_VARIABLES;

class EndGameScreen extends VBox {
    private static EndGameScreen instance = new EndGameScreen();
    private Label playerName;
    private Label balance;
    private Label date;
    private static OptionButton home;


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
        home.setText("HOME");
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
        content.setMaxSize(920,600);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);
        content.setId("EndGameScreenContent");

        getChildren().add(content);
        setAlignment(Pos.CENTER);
        setPrefSize(1024, 768);
    }
    private void setCheckData(String playerName, String playerBalance, String gameDate) {
        instance.playerName.setText(playerName);
        instance.balance.setText(playerBalance+FINAL_GLOBAL_VARIABLES.getCurrencySymbol());
        instance.date.setText(gameDate);
    }

    public static EndGameScreen getInstance() {
        return instance;
    }

    // Back to intro screen.
    static void addTo(Pane pane,String playerName, String playerBalance,String gameDate) {
        instance.setCheckData(playerName,playerBalance,gameDate);
        pane.getChildren().clear();
        pane.getChildren().add(getInstance());
        home.setOnMouseClicked(event -> {
            IntroScreen.addTo(pane);
        });
    }
}
