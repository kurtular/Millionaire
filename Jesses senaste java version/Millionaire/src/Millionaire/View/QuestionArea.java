package Millionaire.View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class QuestionArea extends BorderPane {
    private static QuestionArea questionArea;
    private OptionButton buttonA,buttonB,buttonC,buttonD;
    private TextLabel question,balance;
    private TimerLabel timer;
    private static EventsListner Listner;
    private QuestionArea(){
        super();
        int space = 10;                                                                                                  // Empty space between option buttons.
        this.setPadding(new Insets(0, 0, space, 0));
        question = new TextLabel(790,99);
        buttonA = new OptionButton();
        buttonB = new OptionButton();
        buttonC = new OptionButton();
        buttonD = new OptionButton();
        balance = new TextLabel(494,56);
        timer=new TimerLabel(110, 110);
        timer.setId("timer");

        VBox column1 = new VBox(buttonA,buttonC);
        column1.setSpacing(space);
        column1.setPadding(new Insets(space, 0, space, 35));

        VBox column2 = new VBox(buttonB,buttonD);
        column2.setSpacing(space);
        column2.setPadding(new Insets(space, 35, space, 0));

        this.setTop(question);
        this.setAlignment(question,Pos.TOP_CENTER);

        this.setLeft(column1);
        this.setRight(column2);

        this.setBottom(balance);
        this.setAlignment(balance,Pos.BOTTOM_CENTER);

        this.setCenter(timer);
        this.setMargin(timer,new Insets(-50));

        this.setActions();
    }
//
    protected static void setQuestionAreaListner(EventsListner listner){
        Listner = listner;
    }
//
    private void setActions(){
        buttonA.setOnMouseClicked(event->Listner.setAnswer(this,'A'));
        buttonB.setOnMouseClicked(event->Listner.setAnswer(this,'B'));
        buttonC.setOnMouseClicked(event->Listner.setAnswer(this,'C'));
        buttonD.setOnMouseClicked(event->Listner.setAnswer(this,'D'));
    }
//
    public void enableActions(){
        buttonA.setDisable(false);
        buttonB.setDisable(false);
        buttonC.setDisable(false);
        buttonD.setDisable(false);
    }
//
    public void disableActions(){
        buttonA.setDisable(true);
        buttonB.setDisable(true);
        buttonC.setDisable(true);
        buttonD.setDisable(true);
        buttonA.setOpacity(1);
        buttonB.setOpacity(1);
        buttonC.setOpacity(1);
        buttonD.setOpacity(1);
    }
//
    public void setOptionButtonState(char optionButtonSymbol,int optionButtonState){
        switch (optionButtonSymbol){
            case 'A':buttonA.setState(optionButtonState);break;
            case 'B':buttonB.setState(optionButtonState);break;
            case 'C':buttonC.setState(optionButtonState);break;
            case 'D':buttonD.setState(optionButtonState);break;
            default:
                System.out.println("!!Something went wrong!!\nCheck setOptionButtonState method : View > QuestionArea > setOptionButtonState().");
        }
    }
//
    protected static QuestionArea getInstance(){
        if(questionArea == null)
            questionArea=new QuestionArea();
        return questionArea;
    }
//
    protected static void setQuestionArea(String question,String option1,String option2,String option3,String option4,int balance){
        questionArea.question.setText(question);
        questionArea.buttonA.setText(option1);
        questionArea.buttonB.setText(option2);
        questionArea.buttonC.setText(option3);
        questionArea.buttonD.setText(option4);
        questionArea.balance.setText(balance+" $");
    }
//
    public void setTimer(int value){
        if(value==0)
            this.timer.setText("0");
        else if(value>0 &&value<31)
            this.timer.setText(Integer.toString(value));
        else if(value == -1)
            this.timer.setText(Integer.toString(Integer.parseInt(this.timer.getText())-1));
        else System.out.println("!!Wrong data!!.\nCheck setTimer method location: View > QuestionArea >setTimer()");
    }
}
