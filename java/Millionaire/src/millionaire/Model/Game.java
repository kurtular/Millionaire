package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES;
import org.json.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Game implements getJson {
    // Class variables.
    public final static byte QUESTION_TEXT = 0;
    public final static byte OPTION1 = 1;
    public final static byte OPTION2 = 2;
    public final static byte OPTION3 = 3;
    public final static byte OPTION4 = 4;
    public final static byte CURRENT_QUESTION = 5;
    static private final Game game = new Game();

    public static Game getInstance() {
        return game;
    }

    //The member variables.
    final private Question[] questions = new Question[16];
    private byte currentQuestion;
    private boolean reserveQuestionIsrunning = false;
    final private Player player = Player.getInstance();

    private Game() {
    }

    // To set questions array from the server this method will called inside getJsonData.
    @Override
    public boolean getJsonArray(String data) {
        JSONArray questions = new JSONArray(data);
        for (int i = 0; i < questions.length(); i++) {
            JSONObject question = questions.getJSONObject(i);
            this.questions[i] = new Question(question.getString("questionText"), toStringArray(question.getJSONArray("options")), question.getInt("token"));
        }
        return false;
    }

    public void newGame(String playerName) {
        setQuestions();
        currentQuestion = 1;
        player.setName(playerName);
    }
    // Added safetylevelpayment if player guess at wrong answer.
    public String[] getMoneyCheckData(boolean withDraw) throws SQLException {
        String[] returnedData = new String[3];
        returnedData[0] = player.getName();
        if (withDraw) {
            returnedData[1] = FINAL_GLOBAL_VARIABLES.getPRIZES()[currentQuestion-1];
        }
        else  {
            int safetyLevel = (currentQuestion-1) - ((currentQuestion-1)%5);
            returnedData[1] = FINAL_GLOBAL_VARIABLES.getPRIZES()[safetyLevel];
        }
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        returnedData[2] = date.format(LocalDateTime.now());
        sendToDB(getUserInfo());
        return returnedData;
    }
    // läs Millad
    private void sendToDB(String[] nameandhighscore) throws SQLException {
        DBConnection connectionToDB = new DBConnection();
        Connection connection = connectionToDB.getConnection();
        String sql = "INSERT INTO nameandhighscore(Username,Highscore) VALUES ('"+ nameandhighscore[0] + "','"+ nameandhighscore[1] + "')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql,1);
    }

    // getValue() return a specific string value of the question object depending on value parameter (check class variables above).
    public String getValue(byte value) {
        if (value == 5) {
            return Byte.toString(currentQuestion);
        } else {
            byte currentQuestion;
            if (!reserveQuestionIsrunning) {
                currentQuestion = this.currentQuestion;
            } else {
                currentQuestion = 0;
            }
            return questions[currentQuestion].getValue(value);
        }
    }

    // checkAnswer() will return either true or false depending on the playerAnswer value. ()
    public boolean checkAnswer(char playerAnswer) {
        boolean returnedValue;
        // To be able to check the reserve question answer
        byte currentQuestion;
        if (!reserveQuestionIsrunning) {
            currentQuestion = this.currentQuestion;
        } else {
            currentQuestion = 0;
        }
        switch (playerAnswer) {
            case 'A':
                returnedValue = questions[currentQuestion].checkAnswer((byte) 1);
                break;
            case 'B':
                returnedValue = questions[currentQuestion].checkAnswer((byte) 2);
                break;
            case 'C':
                returnedValue = questions[currentQuestion].checkAnswer((byte) 3);
                break;
            case 'D':
                returnedValue = questions[currentQuestion].checkAnswer((byte) 4);
                break;
            default:
                returnedValue = false;
        }
        return returnedValue;
    }
    //
    public char getCorrectAnswerSymbol(){
        char symbol = '0';
        for (symbol='A';symbol<='D';symbol++){
            if (checkAnswer(symbol)) {
                break;
            }
        }
        return symbol;
    }
    // läs millad
    public void nextQuestion(int second) {
        if (currentQuestion <= FINAL_GLOBAL_VARIABLES.getPRIZES().length) {
            player.addToScore(second,Integer.parseInt(FINAL_GLOBAL_VARIABLES.getPRIZES()[currentQuestion]));
            currentQuestion++;
        }

        if (reserveQuestionIsrunning) {
            reserveQuestionIsrunning = false;
        }
    }

    private void setQuestions() {
        try {
            getJsonText("http://localhost/millionaire/get/");                                                       // Link to the questions rest api.
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // lifeLines
    //
    public String[] changeQuestion() {
        reserveQuestionIsrunning = true;
        String[] returnedResult = {questions[0].getValue(QUESTION_TEXT), questions[0].getValue(OPTION1), questions[0].getValue(OPTION2), questions[0].getValue(OPTION3), questions[0].getValue(OPTION4)};
        return returnedResult;
    }

    //
    public String[] removeHalf() {
        byte currentQuestion;
        if (!reserveQuestionIsrunning) {
            currentQuestion = this.currentQuestion;
        } else {
            currentQuestion = 0;
        }
        //
        byte latestFoundedWrongOptionIndex=-1;
        for (int i = 0; i<2 ; i++){
            boolean foundWrongOption = false;
            while(!foundWrongOption){
                byte rand = (byte)(Math.random()*4+1);
                while (rand == latestFoundedWrongOptionIndex){
                    rand = (byte)(Math.random()*4+1);
                }
                if (!checkAnswer((char) (rand + 64))) {
                    questions[currentQuestion].removeOption((rand));
                    foundWrongOption = true;
                    latestFoundedWrongOptionIndex =rand;
                }
            }
        }
        String[] returnedResult = {getValue(QUESTION_TEXT), getValue(OPTION1), getValue(OPTION2), getValue(OPTION3), getValue(OPTION4)};
        return returnedResult;
    }

    //A method when using lifeLine "Call a friend".
    public String callAFriend() {
        String friendSays = "";
        int rand = (int) (Math.random() * 100);

        //50% of the times your friend is completely sure what the answer is.
        if (rand > 50) {
            friendSays = "Jag är säker på att det är: " + getCorrectAnswerSymbol() + ". " + getValue((byte) (getCorrectAnswerSymbol() - 64));  //  Beacuse of "i" is a char and getValue of the answer demand a byte the method subtract 64 using ASCInumbers.
        }
        // 25% the friend is pretty sure and guessing at the right answer.
        else if (rand > 25) {
            friendSays = "Jag TROR att det är: " + getCorrectAnswerSymbol() + ". " + getValue((byte) (getCorrectAnswerSymbol() - 64));
        }
        //25% the friend is pretty sure but guessing at the wrong answer.
        else {
            boolean isAnswerCorrect;
            while (true){
                int i =(int) (Math.random()*4+1);
                isAnswerCorrect = checkAnswer((char)(i+64));
                String answerText = getValue((byte) (i));
                if (!isAnswerCorrect && answerText!=null) {
                    friendSays = "Jag TROR att det är: " + (char)(i+64) + ". " + answerText;
                    break;
                }
            }
        }
        return "Din vän säger i telefonen:\n" + friendSays;
    }

    public String askAudience() {
        StringBuilder returnedResult = new StringBuilder("Folk röst: \n");
        char[] options = {'A', 'B', 'C', 'D'};
        ArrayList <Character> shownOptions = new ArrayList<>();
        for (int i=0;i<options.length;i++){
            if (getValue((byte) (options[i]-64))!=null)
                shownOptions.add(options[i]);
        }

        int rand = (int) (Math.random() * 100 + 1);
        int max = 100;
        int[] percent = new int[shownOptions.size()];
        byte correctAnswerIndex = 0;


        for (int i = 0; i < shownOptions.size(); i++) {
            if (i == shownOptions.size() - 1) {
                percent[i] = max;
            } else {
                percent[i] =(int) Math.round(Math.random() * max);
                max -= percent[i];
            }
        }
// find the correct answer.
        for (byte i = 0; i < shownOptions.size(); i++) {
            if (checkAnswer(shownOptions.get(i))) {
                correctAnswerIndex = i;
            }
        }
        if (rand < 75) {
            for (int i = 0; i < percent.length; i++) {
                if (percent[correctAnswerIndex] < percent[i]) {
                    int temp = percent[correctAnswerIndex];
                    percent[correctAnswerIndex] = percent[i];
                    percent[i] = temp;

                }
            }
        } else {
            for (int i = 0; i < percent.length; i++) {
                if (percent[correctAnswerIndex] > percent[i]) {
                    int temp = percent[correctAnswerIndex];
                    percent[correctAnswerIndex] = percent[i];
                    percent[i] = temp;
                    break;
                }
            }
        }

        for (byte j=0,i = 0; i < options.length; i++) {
            if (shownOptions.size() != options.length){
                if(j<shownOptions.size() && options[i]==shownOptions.get(j)){
                    returnedResult.append(shownOptions.get(j)).append(" : ").append(percent[j]).append("% ");
                    j++;
                }
                else {
                    returnedResult.append(options[i]).append(" : 0%");
                }

            }
            else {
                returnedResult.append(shownOptions.get(i)).append(" : ").append(percent[i]).append("% ");
            }
        }
        return returnedResult.toString();
    }

    // Läs Millad.
    private String[] getUserInfo(){

        return new String[]{player.getName(),Integer.toString(player.getScore())};
    }
}