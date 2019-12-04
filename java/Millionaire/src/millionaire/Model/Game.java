package millionaire.Model;

import org.json.*;

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
    private boolean reserQuestionIsrunning = false;
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
    public Player getPlayer(){
        return player;
    }

    // getValue() return a specific string value of the question object depending on value parameter (check class variables above).
    public String getValue(byte value) {
        if (value == 5) {
            return Byte.toString(currentQuestion);
        } else {
            return questions[currentQuestion].getValue(value);
        }
    }

    // checkAnswer() will return either true or false depending on the playerAnswer value. ()
    private boolean checkAnswer(char playerAnswer) {
        boolean returnedValue;
        // To be able to check the reserve question answer
        byte currentQuestion;
        if (!reserQuestionIsrunning) {
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
    public boolean checkShownAnswer(char playerAnswer) {
        boolean returnedResult = checkAnswer(playerAnswer);
        if (reserQuestionIsrunning) {
            reserQuestionIsrunning = false;
        }
        return returnedResult;
    }

    public void nextQuestion() {
        currentQuestion++;
    }

    private void setQuestions() {
        try {
            getJsonText("http://localhost/millionaire/get/");                                                       // Link to the questions rest api.
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public String getPlayerName(){
        return player.getName();
    }

    // lifeLines
    //Mohammads kod
    public String[] changeQuestion() {
        reserQuestionIsrunning = true;
        String[] returnedResult = {questions[0].getValue(QUESTION_TEXT), questions[0].getValue(OPTION1), questions[0].getValue(OPTION2), questions[0].getValue(OPTION3), questions[0].getValue(OPTION4)};
        return returnedResult;
    }

    //
    public String[] removeHalf() {
        byte currentQuestion;
        if (!reserQuestionIsrunning) {
            currentQuestion = this.currentQuestion;
        } else {
            currentQuestion = 0;
        }
        for (int i = 0; i < 2; i++) {
            boolean foundWrongOption = false;
            byte latestFoundedWrongOptionIndex = -1;
            while (!foundWrongOption) {
                byte rand = (byte) (Math.random() * 4 + 1);
                while (rand == latestFoundedWrongOptionIndex) {
                    rand = (byte) (Math.random() * 4 + 1);
                }
                if (!checkAnswer((char) (rand + 64))) {
                    questions[currentQuestion].removeOption((rand));
                    foundWrongOption = true;
                }
            }
        }
        String[] returnedResult = {questions[currentQuestion].getValue(QUESTION_TEXT), questions[currentQuestion].getValue(OPTION1), questions[currentQuestion].getValue(OPTION2), questions[currentQuestion].getValue(OPTION3), questions[currentQuestion].getValue(OPTION4)};
        return returnedResult;
    }

    //Henriks kod
    //A method when using lifeLine "Call a friend".
    public String callAFriend() {
        boolean answer;
        String friendSays = "";
        int rand = (int) (Math.random() * 100);

        // To be able to check the reserve question answer
        byte currentQuestion;
        if (!reserQuestionIsrunning) {
            currentQuestion = this.currentQuestion;
        } else {
            currentQuestion = 0;
        }

        //50% of the times your friend is completely sure what the answer is.
        if (rand > 50) {
            for (char i = 'A'; i <= 'D'; i++) {                                                                                  //looping the chars A to D because the method "checkAnswer()" demand chars.
                answer = checkAnswer(i);
                if (answer) {
                    friendSays = "Jag är säker på att det är: " + i + ". " + questions[currentQuestion].getValue((byte) (i - 64));  //  Beacuse of "i" is a char and getValue of the answer demand a byte the method subtract 64 using ASCInumbers.
                    break;
                }
            }
        }
        // 25% the friend is pretty sure and guessing at the right answer.
        else if (rand > 25) {
            for (char i = 'A'; i <= 'D'; i++) {
                answer = checkAnswer(i);
                if (answer) {
                    friendSays = "Jag TROR att det är: " + i + ". " + questions[currentQuestion].getValue((byte) (i - 64));
                    break;
                }
            }
        }
        //25% the friend is pretty sure but guessing at the wrong answer.
        else {
            for (char i = 'A'; i <= 'D'; i++) {
                answer = checkAnswer(i);
                if (!answer) {
                    friendSays = "Jag TROR att det är: " + i + ". " + questions[currentQuestion].getValue((byte) (i - 64));
                    break;
                }
            }

        }

        return "Din vän säger i telefonen:\n" + friendSays;
    }

    public String askAudience() {
        StringBuilder returnedResult = new StringBuilder("Folk röst: \n");
        char[] alternatives = {'A', 'B', 'C', 'D'};
        int rand = (int) (Math.random() * 100 + 1);
        int max = 100;
        int[] percent = new int[4];
        byte correctAnswerIndex = 0;

        for (int i = 0; i < alternatives.length; i++) {
            if (i == alternatives.length - 1) {
                percent[i] = max;
            } else {
                percent[i] = (int) (Math.random() * max + 1);
                max -= percent[i];
            }
        }
// find the correct answer.
        for (byte i = 0; i < alternatives.length; i++) {
            if (checkAnswer(alternatives[i])) {
                correctAnswerIndex = i;
                break;
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
        for (byte i = 0; i < alternatives.length; i++) {
            returnedResult.append(alternatives[i]).append(" : ").append(percent[i]).append("% ");
        }
        return returnedResult.toString();
    }
}