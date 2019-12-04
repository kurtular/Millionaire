package millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import millionaire.Controller;

public class EndGameScreen extends VBox {
    private static EndGameScreen instance = new EndGameScreen();
    private Label label1;
    private Label label2;
    private Label label3;
    private static OptionButton home;


    private EndGameScreen() {
        super();

        label1 = new Label();
        label2 = new Label();
        label3 = new Label();


        label1.setTranslateX(350);
        label1.setTranslateY(45);
        label1.setFont(Font.font(30));

        label2.setTranslateX(270);
        label2.setTranslateY(35);
        label2.setFont(Font.font(30));

        label3.setTranslateX(170);
        label3.setTranslateY(10);
        label3.setFont(Font.font(30));

        home = new OptionButton();
        home.setText("HOME");
        setPrefSize(900, 400);
        getStylesheets().add("style.css");

        Image imageView = new Image("img/moneycheck.png");
        ImageView imageView2 = new ImageView(imageView);

        imageView2.setFitHeight(400);
        imageView2.setFitWidth(900);

        VBox vBox = new VBox(label1, label2, label3);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setSpacing(60);

        StackPane pane = new StackPane();
        pane.getChildren().addAll(imageView2, vBox);
        pane.setId("pane");
        getChildren().addAll(pane, home);
        this.setSpacing(70);
        this.setMinWidth(1024);
        this.setMinHeight(768);
        this.setAlignment(Pos.CENTER);
    }

   public static void setCheck(String name, String balance, String date) {
        instance.label1.setText(name);
        instance.label2.setText(balance);
        instance.label3.setText(date);
    }

    public static EndGameScreen getInstance() {
        return instance;
    }

    // Back to intro screen.
    static void addTo(Pane pane) {
        pane.getChildren().add(getInstance());
        home.setOnMouseClicked(event -> {
            IntroScreen.addTo(pane);
            Controller.getInstance().startTheGame("testplayer");
        });
    }
}
