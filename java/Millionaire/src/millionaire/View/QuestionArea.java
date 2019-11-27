package millionaire.View;

import millionaire.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import millionaire.FINAL_GLOBAL_VARIABLES;

public class QuestionArea extends BorderPane {
    final private static QuestionArea instance = new QuestionArea();
    private final OptionButton buttonA;
    private final OptionButton buttonB;
    private final OptionButton buttonC;
    private final OptionButton buttonD;
    private final TextLabel question;
    private final TextLabel balance;
    private final TimerLabel timer;

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
        timer = TimerLabel.getInstance();
        timer.setId("timer");

        VBox column1 = new VBox(buttonA,buttonC);
        column1.setSpacing(space);
        column1.setPadding(new Insets(space, 0, space, 35));

        VBox column2 = new VBox(buttonB,buttonD);
        column2.setSpacing(space);
        column2.setPadding(new Insets(space, 35, space, 0));

        this.setTop(question);
        setAlignment(question,Pos.TOP_CENTER);

        this.setLeft(column1);
        this.setRight(column2);

        this.setBottom(balance);
        setAlignment(balance,Pos.BOTTOM_CENTER);

        this.setCenter(timer);
        setMargin(timer,new Insets(-50));

        this.setActions();
    }

//
    private void setActions(){
        buttonA.setOnMouseClicked(event-> Controller.getInstance().setAnswer('A'));
        buttonB.setOnMouseClicked(event-> Controller.getInstance().setAnswer('B'));
        buttonC.setOnMouseClicked(event-> Controller.getInstance().setAnswer('C'));
        buttonD.setOnMouseClicked(event-> Controller.getInstance().setAnswer('D'));
    }
//
    void enableActions(){
        buttonA.setDisable(false);
        buttonB.setDisable(false);
        buttonC.setDisable(false);
        buttonD.setDisable(false);
    }
//
    void disableActions(){
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
    void setOptionButtonState(char optionButtonSymbol,int optionButtonState){
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
    public static QuestionArea getInstance(){
        return instance;
    }
//
    void updateQuestion(String question, String option1, String option2, String option3, String option4, String balance){
        instance.question.setText(question);
        instance.buttonA.setText(option1);
        instance.buttonB.setText(option2);
        instance.buttonC.setText(option3);
        instance.buttonD.setText(option4);
        instance.balance.setText(balance+ FINAL_GLOBAL_VARIABLES.getCurrencySymbol());
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
