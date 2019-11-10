package Millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class QuestionArea extends BorderPane {
    private static QuestionArea questionArea;
    private OptionButton buttonA,buttonB,buttonC,buttonD;
    private textLabel question,balance;
    private TimerLabel timer;
    private static GuiListners questionAreaListner;

    private QuestionArea(){
        super();
        int space = 10;                                                                                                  // Empty space between option buttons.
        this.setPadding(new Insets(0, 0, space, 0));
        question = new textLabel(790,99);
        buttonA = new OptionButton();
        buttonB = new OptionButton();
        buttonC = new OptionButton();
        buttonD = new OptionButton();
        balance = new textLabel(494,56);
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
    protected static void setQuestionAreaListner(GuiListners listner){
        questionAreaListner = listner;

    }
    //
    private void setActions(){
        buttonA.setOnMouseClicked(event->questionAreaListner.setAnswer(this,'A'));
        buttonB.setOnMouseClicked(event->questionAreaListner.setAnswer(this,'B'));
        buttonC.setOnMouseClicked(event->questionAreaListner.setAnswer(this,'C'));
        buttonD.setOnMouseClicked(event->questionAreaListner.setAnswer(this,'D'));
    }
    //
    protected void enableActions(){
        buttonA.setDisable(false);
        buttonB.setDisable(false);
        buttonC.setDisable(false);
        buttonD.setDisable(false);
    }
    //
    protected void disableActions(){
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
    protected static QuestionArea createQuestionArea(){
        if(questionArea == null)
            questionArea=new QuestionArea();
        return questionArea;
    }
    //
    public void setQuestionArea(String question,String option1,String option2,String option3,String option4,int balance){
        this.question.setText(question);
        this.buttonA.setText(option1);
        this.buttonA.setText(option2);
        this.buttonA.setText(option3);
        this.buttonA.setText(option4);
        this.balance.setText(balance+" $");
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
