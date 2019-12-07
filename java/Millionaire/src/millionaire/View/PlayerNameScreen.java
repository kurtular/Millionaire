package millionaire.View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import millionaire.Controller;

import static javafx.geometry.Pos.CENTER;


class PlayerNameScreen extends VBox {
    final private static PlayerNameScreen instance = new PlayerNameScreen();

    private static  Label errorMessage;
    private static TextField aliasInput;
    private static OptionButton confirmButton;

    static PlayerNameScreen getInstance() {
        return instance;
    }

    private PlayerNameScreen() {
        super();

        ImageView imageView = new ImageView("img/player.png");
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);

        aliasInput = new TextField();
        aliasInput.setPromptText("Skriv in ditt alias:");
        aliasInput.setFocusTraversable(false);
        aliasInput.setMaxSize(250, 20);
        aliasInput.setFont(Font.font(15));

        Label hintText = new Label();
        hintText.setText("Detta alias kommer sedan synas publikt tillsammans med ditt resultat.");
        hintText.setPrefSize(350, 60);
        hintText.setFont(Font.font("arial", 15));
        hintText.setWrapText(true);
        hintText.setTextAlignment(TextAlignment.CENTER);
        hintText.setTextFill(Color.valueOf("#fff"));

        errorMessage = new Label();
        errorMessage.setTextFill(Color.RED);

        confirmButton = new OptionButton();
        confirmButton.setText("strata spelet");
        confirmButton.setScaleX(0.7);
        confirmButton.setScaleY(0.7);
        confirmButton.setFont(Font.font(30));

        VBox innerVBox = new VBox(imageView,aliasInput,hintText,errorMessage,confirmButton);
        innerVBox.setId("vBox");
        innerVBox.setAlignment(CENTER);
        innerVBox.setSpacing(40);
        innerVBox.setMaxSize(500, 700);
        innerVBox.setPadding(new Insets(10,0,0,0));

        getChildren().add(innerVBox);
        setAlignment(CENTER);
        setPrefSize(1024, 768);

    }

    static void addTo(Pane pane) {
        pane.getChildren().clear();
        pane.getChildren().add(getInstance());
        confirmButton.setOnMouseClicked(event -> {
            if (aliasInput.getText().isEmpty()) {
                errorMessage.setText("Du måste ange ett alias!");
            }
            else {
                SoundEffects.getInstance("letsPlay").play();
                Controller.getInstance().startTheGame(aliasInput.getText());
                PlayScreen.addTo(pane);
            }
            });
        }
    }

