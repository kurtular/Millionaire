import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player implements  playerView {
    private Scanner scanner = new Scanner(System.in);

    public String getUserName(){
        displayOneLineMessage("What is your name?");
        return scanner.nextLine().trim();
    }


    @Override
    public void displayErrorMessage(String message) {

    }
    
    public void displayIntro() {

    }

    public void displayQuestion(Question question){

    }
    public void displayAnswer(Answer answer){

    }

    // TODO: take in a list of life line (friend and audience)
    public void displayLifeLines(){

    }

    public void displayCallFriendResults(){
        displayOneLineMessage("Display friends alternative");
    }

    public void displayAudienceResults(){
        displayOneLineMessage("Display audience alternative");
    }

    public void displayOneLineMessage(String promptText){
        System.out.println(promptText);
    }

    @Override
    public boolean getIsFinalAnswer() {
        return false;
    }

}
