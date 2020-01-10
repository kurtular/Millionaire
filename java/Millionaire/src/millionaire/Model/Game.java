package millionaire.Model;

import millionaire.FINAL_GLOBAL_VARIABLES;
import millionaire.FINAL_GLOBAL_VARIABLES.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// import third-party library. This used to be able to handle Json array,object that will be fetched from the server.
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The main class of the Model package and it's represent the Model part of the MVC design pattern.
 *
 * @author Mohammad, Henrik, Millad, Jesse
 */
public class Game implements GetJson {
    //           >>>>Class variables and methods.<<<<
    // The following variable and method created to apply singleton design pattern.
    /**
     * It is the only instance of this class (singleton).
     */
    static private final Game game = new Game();

    /**
     *  It returns change question life line object.
     * @return the only possible instance of this class (singleton).
     */
    public static Game getInstance() {
        return game;
    }

    /////////////////////////////////////////////////////////////
    //              >>>>Member variables.<<<<

    final Question[] questions = new Question[16];
    byte currentQuestion;
    final private Player player = Player.getInstance();
    final ChangeQuestion changeQuestion = ChangeQuestion.getInstance();
    private final LifeLine removeHalf = LifeLine.getInstance(LifeLineType.REMOVE_HALF);
    private final LifeLine callAFriend = LifeLine.getInstance(LifeLineType.CALL_A_FRIEND);
    private final LifeLine askAudience = LifeLine.getInstance(LifeLineType.ASK_AUDIENCE);
    ////////////////////////////////////////////////////////////
    //              >>>>Class constructor.<<<<

    /**
     * It is a constructor method creates a Game object.
     */
    private Game() {
    }

    /////////////////////////////////////////////////////////////
    //                >>>> Member methods.<<<<

    // To set questions array from the server.todo after asking Rickard
    @Override
    public void setupJsonData(String data) {
        JSONArray questions = new JSONArray(data);
        for (int i = 0; i < questions.length(); i++) {
            JSONObject question = questions.getJSONObject(i);
            this.questions[i] = new Question(question.getString("questionText"), toStringArray(question.getJSONArray("options")), question.getInt("token"));
        }
    }

    /**
     * prepare game object so the user can start new game and reset player name.
     * @param playerName create a player name so user can input ID after the game is reset.
     */
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

    /**
     *  gets balance/date/name and current question to the writercheck
     * @param withDraw if withDraw is true the player wont be send back below safety level
     * @return  data from users game
     */
    //
    public String[] getMoneyCheckData(boolean withDraw) {
        String[] returnedData = new String[3];
        returnedData[0] = player.getName();
        if (withDraw) {
            returnedData[1] = FINAL_GLOBAL_VARIABLES.getPRIZES()[currentQuestion - 1];
        } else {
            int safetyLevel = (currentQuestion - 1) - ((currentQuestion - 1) % 5);
            returnedData[1] = FINAL_GLOBAL_VARIABLES.getPRIZES()[safetyLevel];
        }
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        returnedData[2] = date.format(LocalDateTime.now());
        try {
            sendToDB(returnedData);
        } catch (Exception e) {
            System.err.println("!!Couldn't send player log (score) to db!!");
        }

        return returnedData;
    }


    /**
     * This method will send information from javafx to database
     * @param moneyCheckData An array of string where it contain player's name,balance and date.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */
    private void sendToDB(String[] moneyCheckData) throws SQLException {
        DBConnection connectionToDB = new DBConnection();
        Connection connection = connectionToDB.getConnection();
        String sql = "INSERT INTO high_score(player_name,player_balance,player_score) VALUES ('" + moneyCheckData[0] + "','" + Integer.parseInt(moneyCheckData[1].replaceAll(" ", "")) + "','" + player.getScore() + "')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        connection.close();
    }

    /**
     * gets question running if user answer wrong ends the game and calculate score
     * @param QuestionPart question stage of the game
     * @return current question and score value
     */
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

    /**
     *
     * @param playerAnswer checks if player answer is true or false
     * @return false answer
     */

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

    /**
     * create method that the right answer will have matching symbol
     * @return right symbol.
     */

    //
    public char getCorrectAnswerSymbol() {
        char symbol;
        for (symbol = 'A'; symbol <= 'D'; symbol++) {
            if (checkAnswer(symbol)) {
                break;
            }
        }
        return symbol;
    }

    /**
     * create a method that makes game move on to the next question after users answer
     * @param second adds points depending on how much seconds it takes for the user to answer
     */

    //
    public void nextQuestion(int second) {
        if (currentQuestion <= FINAL_GLOBAL_VARIABLES.getPRIZES().length) {
            player.addToScore(second, Integer.parseInt(FINAL_GLOBAL_VARIABLES.getPRIZES()[currentQuestion].replaceAll(" ", "")));
            currentQuestion++;
        }
        if (changeQuestion.isRunning()) {
            changeQuestion.stop();
        }
    }



    //
    private void setQuestions() {
        try {
            getJsonText("http://mohammad-ahmad.se/millionaire/get/");
            // The following commented code can be used for locally hosted backend instead of the above one.
            //getJsonText("http://localhost/millionaire/get/");                                                       // Link to the questions rest api.
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Running the selected lifeline
     *
     * @param lifeLineType refers to a life line that will run.
     * @return the different string arrays with hints or question changes.
     */
    public String[] runLifeLine(String lifeLineType) {
        String[] returnedResult;
        switch (lifeLineType) {
            case LifeLineType.CHANGE_QUESTION:
                returnedResult = changeQuestion.run();
                break;
            case LifeLineType.REMOVE_HALF:
                returnedResult = removeHalf.run();
                break;
            case LifeLineType.CALL_A_FRIEND:
                returnedResult = callAFriend.run();
                break;
            case LifeLineType.ASK_AUDIENCE:
                returnedResult = askAudience.run();
                break;
            default:
                System.err.println("There is no lifeline related to (" + lifeLineType + ").");
                returnedResult = null;
                break;
        }
        return returnedResult;
    }
}