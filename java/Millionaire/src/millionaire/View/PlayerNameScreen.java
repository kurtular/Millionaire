package millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import millionaire.Controller;

import static javafx.geometry.Pos.CENTER;


class PlayerNameScreen extends VBox {
    final private static PlayerNameScreen instance = new PlayerNameScreen();
    private final Label hintText;
    private static  Label errorMessage;
    private static TextField aliasInput;
    private static TextLabel confirmButton;
    private final Image playerImage;
    private final ImageView imageView;
    private final VBox innerVBox;


    static PlayerNameScreen getInstance() {
        return instance;
    }

    private PlayerNameScreen() {
        super();
        playerImage = new Image("img/player.png");
        imageView = new ImageView(playerImage);
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);
        aliasInput = new TextField();
        aliasInput.setPromptText("Skriv in ditt alias:");
        aliasInput.setFocusTraversable(false);
        aliasInput.setMaxSize(150, 20);
        aliasInput.setFont(Font.font(15));
        hintText = new Label();
        hintText.setText("Detta alias kommer sedan synas publikt tillsammans med ditt resultat ");
        hintText.setPrefSize(350, 60);
        hintText.setFont(Font.font("arial", 11));
        hintText.setTextFill(Color.valueOf("#fff"));
        errorMessage = new Label();
        errorMessage.setTextFill(Color.RED);
        confirmButton = new TextLabel(200, 50);
        confirmButton.setText("Registrera spel");

        innerVBox = new VBox(imageView,aliasInput,hintText,errorMessage,confirmButton);
        innerVBox.setId("vBox");
        innerVBox.setAlignment(CENTER);
        innerVBox.setSpacing(40);
        innerVBox.setPadding(new Insets(20));

        getChildren().add(innerVBox);
        setMargin(innerVBox,new Insets(100,200,100,330));




    }

    static void addTo(Pane pane) {
        pane.getChildren().clear();
        pane.getChildren().add(getInstance());
        confirmButton.setOnMouseClicked(event -> {
            if (aliasInput.getText().isEmpty()) {
                errorMessage.setText("Du måste ange ett alias!");
            }
            else {
                Controller.getInstance().startTheGame(aliasInput.getText());
                PlayScreen.addTo(pane);
            }
            });
        }
    }

