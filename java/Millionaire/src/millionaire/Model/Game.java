package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES;
import org.json.JSONArray;
import org.json.JSONObject;
import millionaire.FINAL_GLOBAL_VARIABLES.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Mohammad, Henrik, Millad, Jesse
 */
public class Game implements getJson {
    static private final Game game = new Game();
//Mohammad
    public static Game getInstance() {
        return game;
    }

    //The member variables.
    final Question[] questions = new Question[16];
    byte currentQuestion;
    final private Player player = Player.getInstance();
    ChangeQuestion changeQuestion = ChangeQuestion.getInstance();
    private LifeLine removeHalf =  LifeLine.getInstance(LifeLineType.REMOVE_HALF);
    private LifeLine callAFriend = LifeLine.getInstance(LifeLineType.CALL_A_FRIEND);
    private LifeLine askAudience = LifeLine.getInstance(LifeLineType.ASK_AUDIENCE);
//Mohammad
    private Game() { }

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
        currentQuestion = 1;
        setQuestions();
        player.resetPlayer();
        askAudience.reset();
        callAFriend.reset();
        changeQuestion.reset();
        removeHalf.reset();
        player.setName(playerName);
    }
    //
    public String[] getMoneyCheckData(boolean withDraw) {
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
        try {
            sendToDB(returnedData);
        }catch (Exception e){
            System.err.println("!!Couldn't send player log (score) to db!!"+e);
        }

        return returnedData;
    }
    private void sendToDB(String[] moneyCheckData) throws SQLException {
        DBConnection connectionToDB = new DBConnection();
        Connection connection = connectionToDB.getConnection();
        String sql = "INSERT INTO high_score(player_name,player_balance,player_score) VALUES ('"+ moneyCheckData[0] + "','"+ Integer.parseInt(moneyCheckData[1].replaceAll(" ","" )) + "','" + player.getScore() + "')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

// getValue() return a specific string value of the question object depending on value parameter (check class variables above).
    public String getQuestionPart(byte QuestionPart) {
        if (QuestionPart == 5) {
            return Byte.toString(currentQuestion);
        } else {
            byte currentQuestion;
            if (!changeQuestion.isRunning()) {
                currentQuestion = this.currentQuestion;
            } else {
                currentQuestion = 0;
            }
            return questions[currentQuestion].getValue(QuestionPart);
        }
    }

// checkAnswer() will return either true or false depending on the playerAnswer value. ()
    public boolean checkAnswer(char playerAnswer) {
        boolean returnedValue;
        // To be able to check the reserve question answer
        byte currentQuestion;
        if (!changeQuestion.isRunning()) {
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
        char symbol;
        for (symbol='A';symbol<='D';symbol++){
            if (checkAnswer(symbol)) {
                break;
            }
        }
        return symbol;
    }
//
    public void nextQuestion(int second) {
        if (currentQuestion <= FINAL_GLOBAL_VARIABLES.getPRIZES().length) {
            player.addToScore(second,Integer.parseInt(FINAL_GLOBAL_VARIABLES.getPRIZES()[currentQuestion].replaceAll(" ", "")));
            currentQuestion++;
        }
        if (changeQuestion.isRunning()) {
            changeQuestion.stop();
        }
    }
//
    private void setQuestions() {
        try {
            getJsonText("http://localhost/millionaire/get/");                                                       // Link to the questions rest api.
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

//
public String[] runLifeLine(String lifeLineType) {
    String[] returnedResult;
    if (lifeLineType.equals(LifeLineType.CHANGE_QUESTION)) {
        returnedResult = changeQuestion.run();
    } else if (lifeLineType.equals(LifeLineType.REMOVE_HALF)) {
        returnedResult = removeHalf.run();
    } else if (lifeLineType.equals(LifeLineType.CALL_A_FRIEND)) {
        returnedResult = callAFriend.run();
    } else if (lifeLineType.equals(LifeLineType.ASK_AUDIENCE)) {
        returnedResult = askAudience.run();
    } else {
        System.err.println("There is no lifeline related to (" + lifeLineType + ").");
        returnedResult = null;
    }
    return returnedResult;
}
}