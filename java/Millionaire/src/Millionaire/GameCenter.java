import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class GameCenter {
    Scanner scanner = new Scanner(System.in);
    // money table, 15 prices from 1- 1 million
    private int[] money = new int [15];
    private Question question = new Question();
    private Question[] questionList = new Question[15];
   private  int timeInSecond = 0;
    Timer timer = new Timer();
    public GameCenter(){
        displayIntro();
        loadGame();
    }
    private void loadGame(){
        choosePlayer();
        chooseTypeOfQuestion();
        startGame();
    }
    private void choosePlayer(){
        int choice;
        String name;
        String secondName;
        choice = scanner.nextInt();
        switch (choice){
            case 1:
                System.out.println("Please enter your name");
                name = scanner.next();
                Player player = new Player(name,0,0,0);
                break;
            case 2:
                System.out.println("Please enter name player 1");
                name = scanner.next();
                System.out.println("Please enter name player 2");
                secondName =scanner.next();
                Player[] players = new Player[2];
                players[0] = new Player(name,0,0,0);
                players[1] = new Player(secondName,0,0,0);
                break;
            default:
                System.out.println("Invalid input");
        }
    }
    // give instruction to player here
    private void displayIntro(){
        System.out.println("Welcome to Millionaire quiz game");
        System.out.println("Press 1: Single player Press 2: player vs player");
    }
    private void displayHelp(){
        // display all the help player have
    }
    private  void chooseTypeOfQuestion(){
        int choice;
        System.out.println("Press 1: Html Questions Press 2: Css Questions Press 3: Java Question Press 4: JavaScript Question");
        choice = scanner.nextInt();
        switch (choice){
            case 1:
                loadQuestion(question.easyQuestionHtml,question.answer,1);
                loadQuestion(question.mediumQuestionHtml,question.answer,2);
                loadQuestion(question.hardQuestionHtml,question.answer,3);
                break;
            case 2:
                loadQuestion(question.easyQuestionCss,question.answer,1);
                loadQuestion(question.mediumQuestionCss,question.answer,2);
                loadQuestion(question.hardQuestionCss,question.answer,3);
                break;
            case 3:
                loadQuestion(question.easyQuestionJava,question.answer,1);
                loadQuestion(question.mediumQuestionJava,question.answer,2);
                loadQuestion(question.hardQuestionJava,question.answer,3);
                break;
            case 4:
                loadQuestion(question.easyQuestionJavaScript,question.answer,1);
                loadQuestion(question.mediumQuestionJavaScript,question.answer,2);
                loadQuestion(question.hardQuestionJavaScript,question.answer,3);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
    // initiate 15 question with answer. Take in a list of question and four Strings and the number to indicate easy, medium and hard.
    private void loadQuestion (String [] inputQuestionList, String[] inputAnswersList, int num){
        // create easy 5 question with 4 answer
        if(num==1) {
            for(int i = 0; i<5;i++) {
                questionList[i] = new Question(inputQuestionList[i], inputAnswersList[0],inputAnswersList[1],inputAnswersList[2],inputAnswersList[3]);
            }
            // create medium 5 question with 4 answer
        }else if(num==2){
            for (int i = 5; i < 10; i++) {
                questionList[i] = new Question(inputQuestionList[i],inputAnswersList[0],inputAnswersList[1],inputAnswersList[2],inputAnswersList[3]);
            }
        }
        // create hard 5 question with 4 answer
        else if(num==3){
            for (int i = 10; i < 15; i++) {
                questionList[i] = new Question(inputQuestionList[i],inputAnswersList[0],inputAnswersList[1],inputAnswersList[2],inputAnswersList[3]);
            }
        }

    }
    // display question
    private void displayQuestion(){
        for(int i =0; i<questionList.length;i++){
            System.out.println(questionList.toString());
        }
    }
    private  void startGame(){

    }
    // set time
    private void setTimer (){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeInSecond++;
                System.out.println(timeInSecond);
                // if player could not answer in 30 second
                if(timeInSecond>30){
                    System.out.println("Times out");
                }
            }
        };

        timer.scheduleAtFixedRate(task,1000,1000);
    }

    private void highScoreSetter(){
        //
    }
    private boolean  askHelp(){
        // if no help left. Display you don't help left
        // if player do have help. Display help.

        return true;
    }

    private boolean checkAnswer(){
        // if the player have wrong answer. Ask if they want to use help.
        return true;
    }
}
