package Millionaire;

import Millionaire.Model.Game;
import Millionaire.View.*;

import static Millionaire.View.LifeLineArea.lifeLineArea;
import static Millionaire.View.LifeLineHintArea.lifeLineHintArea;


// Controller class ( MVC design pattern).
// game variable represents the Model of MVC design pattern.
// gui variable represents the View of MVC design pattern.
public class Controller {
// Controller member variables.
    private static Gui gui;
    private static Game game;

///////////////////////////////////////////////////////////
// Controller constructor.
    Controller(String args[]){
        gui = new Gui();
        gui.setEventsListner(setGuiListners());
        gui.launchGui(args);
    }

///////////////////////////////////////////////////////////
// setGuiListners() returns definition of  GuiListners (interface) that will be called from other components that are inside view.
// ((I using this way to be able to make the communication between the View and Model inside the Controller depending on the actions type)).

    private static EventsListner setGuiListners(){
        EventsListner guiListners = new EventsListner() {
            @Override
            public void startTheGame() {
                game = new Game();
                setQuestion();                                                                                  //TODO checking if the game is full loaded before show game gui
            }

// setAnswer() will be called when the player will select an answer for the question.
// It's make some changes that fits the situation for example: making the background color of the selection button yellow and disable the other selection.
            @Override
            public void setAnswer(QuestionArea questionArea,char buttonSymbol) { // TODO PlayContent instead of QuestionArea and add delay to button effects-+
                questionArea.setOptionButtonState(buttonSymbol,OptionButton.Checking);
                questionArea.disableActions();
                if (game.checkAnswer(buttonSymbol)) {
                    questionArea.setOptionButtonState(buttonSymbol, OptionButton.Right);
                    game.nextQuestion();
                    setQuestion();
                    questionArea.enableActions();
                    questionArea.setOptionButtonState(buttonSymbol, OptionButton.Default);
                } else {
                    questionArea.setOptionButtonState(buttonSymbol, OptionButton.Wrong);
                }
            }
            @Override
            public void setLifeLine(LifeLineArea lifeLineArea, String lifeLineSelection) {
                System.out.println(lifeLineSelection);
                setLifeLineImgState(lifeLineSelection);
                lifeLineArea.disableActions();
            }};
        return guiListners;
    }
// setQuestion() method brings the question and its options and send them to View (gui) that will show it on the screen.
    private static void setQuestion(){
        String question = game.getValue(Game.QuestionText);
        String option1 = game.getValue(Game.Option1);
        String option2 = game.getValue(Game.Option2);
        String option3 = game.getValue(Game.Option3);
        String option4 = game.getValue(Game.Option4);
        gui.updateQuestion(question, option1, option2, option3, option4, 6); //TODO balance
    }
    public static void setLifeLineImgState(String lifeLineSelection){
        switch (lifeLineSelection){
            case "askThePeople":
                LifeLineArea.imgView0.setImage(LifeLineArea.img4);break;
            case "callAFriend":
                LifeLineArea.imgView1.setImage(LifeLineArea.img5);
                displayPhoneAFriend();
                break;

            case "removeHalf":
                LifeLineArea.imgView2.setImage(LifeLineArea.img6);break;
            case "changeQuestion":
                LifeLineArea.imgView3.setImage(LifeLineArea.img7);break;
            default:
                System.out.println("!!Something went wrong!!\nCheck setLifeLineImgState method : View > LifeLineArea > setLifeLineImgState().");
        }
    }
    public static void displayPhoneAFriend() {
        System.out.println(game.phoneAFriend());
        lifeLineHintArea.label0.setText(game.phoneAFriend());
       // playScreen.setCenter(LifeLinePollArea.getInstance());

    }
}