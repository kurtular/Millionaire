package millionaire.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import millionaire.FINAL_GLOBAL_VARIABLES;

/**
 * @author Mohammad, Joakim, Henrik, Jesse
 */
// The main class of the View packet and it's represent the View class of the MVC design pattern.
public class Gui extends Application {
    static private Pane content;
// start() will be run after calling launch(args) that is inside launchGui(). It's an overridden method after extending Application class. (javaFx).
    @Override
    public void start(Stage stage) {
        content=new Pane();
// set the game intro (IntroScreen) and add it to .
        IntroScreen.addTo(content);
        Scene scene = new Scene(content);
        stage.setScene(scene);

// set some properties values for the stage object like a title ,frame icon and game frame dimensions(min).
        stage.setTitle("Miljonär");
        scene.getStylesheets().add("style.css");
        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/logo.png"));
        stage.show();

    }

// to launch the gui of the game.
    public void launchGui(String[] args){
        launch(args);
    }

/* updateQuestion() method pass the question that will be shown to the PlayContent class (window will be shown during the match)
   and to enable temporarily disabled lifelines.*/
   public void updateQuestion(String question,String option1,String option2,String option3,String option4,byte currentQuestion){
//        LifeLineArea.getInstance().enableTempDisabledLifeLines();
        PriceTable.getInstance().setCurrentPlace(currentQuestion);
        String balance = FINAL_GLOBAL_VARIABLES.getPRIZES()[currentQuestion-1];
        QuestionArea.getInstance().updateQuestion(question, option1, option2, option3, option4, balance,currentQuestion);
        //
        TimerLabel.getInstance().resetTimer();
        TimerLabel.getInstance().startTimer();
        enableActions();
   }
//
    public void setOptionButtonState(char optionButtonSymbol,int optionButtonState){
        QuestionArea.getInstance().setOptionButtonState(optionButtonSymbol,optionButtonState);
    }

    /**
     * Enable the actionevents in different viewclasses.
     */
    private void enableActions(){
            PlayScreen.getInstance().enableWithdrawing();
            QuestionArea.getInstance().enableActions();
            LifeLineArea.getInstance().activate();
    }

    /**
     * Disable the actionevents in different viewclasses.
     */
    public void disableActions(){
        PlayScreen.getInstance().disableWithdrawing();
        QuestionArea.getInstance().disableActions();
        LifeLineArea.getInstance().deactivateTemporarily();
    }

    /**
     * Push the hint forward, reset timer, enable actions and play a sound.
     * @param hint The lifeline hint
     * @param currentQuestion which number of the question
     */
    public void setLifeLineHint(String hint,byte currentQuestion) {
       LifeLineArea.getInstance().setLifeLineHint(hint);
        //
        if (!hint.isEmpty()){
        TimerLabel.getInstance().resetTimer();
        TimerLabel.getInstance().startTimer();
        enableActions();
        QuestionArea.getInstance().playSound(currentQuestion);
        }
    }
//
    public void showEndGameScreen(String playerName, String playerBalance,String gameDate){
        EndGameScreen.addTo(content,playerName,playerBalance,gameDate);
    }
    //
    public void stopTimer(){
       TimerLabel.getInstance().stopTimer();
    }
    //
    public byte getShownSeconds(){
       return TimerLabel.getInstance().getShownSeconds();
    }
    //
    public void playSound(String effectName){
       SoundEffectPlayer.play(effectName);
    }
    //
    static void reset(){
       PriceTable.getInstance().resetPriceTable();
       LifeLineArea.getInstance().resetLifeLineArea();
       QuestionArea.getInstance().resetQuestionArea();
    }
}
