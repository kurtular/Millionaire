package millionaire.View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import millionaire.Controller;
import millionaire.Timer;

import static javafx.geometry.Pos.CENTER;

/**
 * Singleton class for showing the userinput dialog before starting the game. This class is a VBox.
 *
 * @author Henrik, Joakim.
 */
class AliasScreen extends VBox {
    /**
     * It is the only instance of this class (singleton).
     */
    final private static AliasScreen instance = new AliasScreen();

    private final Label errorMessage;
    private final TextField aliasInput;
    private final OptionButton confirmButton;

    /**
     * The constructor using JavaFX to build the graphics including playerimage and textfield.
     */
    private AliasScreen() {
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
        confirmButton.setText("Starta spelet");
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

    /**
     * This method checking if textfield is empty and creating errormessage if so.
     * @return false if its not empty and true if its empty
     */
    private boolean aliasInputISEmpty(){
        if(aliasInput.getText().isEmpty()){
            errorMessage.setText("Du måste ange ett alias!");
            return true;
        }
        return false;
    }

    /**
     * Method that resets aliasInput to default state when starting new game.
     */
    private void reset(){
        // To make aliasInput enabled and empty (reset).
        aliasInput.setDisable(false);
        aliasInput.setText("");
        confirmButton.setDisable(false);
    }

    /**
     * Add object to playscreen when pressing enter or click at confirmbutton.
     */
    static void show() {
        instance.reset();
        Gui.content.getChildren().clear();
        Gui.content.getChildren().add(instance);
        instance.aliasInput.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                //
                if (!instance.aliasInputISEmpty()) {
                    instance.aliasInput.setDisable(true);
                    instance.confirmButton.setDisable(true);
                    instance.errorMessage.setText(null);
                    //
                    Timer.delay(() -> {
                        try{
                            Controller.getInstance().startTheGame(instance.aliasInput.getText());
                            PlayScreen.show();
                        }
                    catch (Exception e){
                        instance.errorMessage.setText("Kan ej starta spelet!!\nVänligen anslut datorn till nätverket.");
                        instance.errorMessage.setTextAlignment(TextAlignment.CENTER);
                        instance.confirmButton.setDisable(false);
                        instance.aliasInput.setDisable(false);
                    }}, 3);
                }
            }
        });
        instance.confirmButton.setOnMouseClicked(event -> {
            //
            if (!instance.aliasInputISEmpty()) {
                instance.aliasInput.setDisable(true);
                instance.confirmButton.setDisable(true);
                instance.errorMessage.setText(null);
                Timer.delay(() -> {
                    try{
                        Controller.getInstance().startTheGame(instance.aliasInput.getText());
                        PlayScreen.show();
                    }
                    catch (Exception e){
                        instance.errorMessage.setText("Kan ej starta spelet!!\nVänligen anslut datorn till nätverket.");
                        instance.errorMessage.setTextAlignment(TextAlignment.CENTER);
                        instance.confirmButton.setDisable(false);
                        instance.aliasInput.setDisable(false);
                    }}, 3);
            }
        });
    }
}