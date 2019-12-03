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
    public void newGame(String playerName){
        setQuestions();
        currentQuestion=1;
        player.setName(playerName);
    }
    // getValue() return a specific string value of the question object depending on value parameter (check class variables above).
    public String getValue(byte value) {
        if(value==5) {
            return Byte.toString(currentQuestion);
        }else{
            return questions[currentQuestion].getValue(value);}
    }

    // checkAnswer() will return either true or false depending on the playerAnswer value. ()
    public boolean checkAnswer(char playerAnswer) {
        boolean returnedValue;
        // To be able to check the reserve question answer
        byte currentQuestion;
        if (!reserQuestionIsrunning){
            currentQuestion=this.currentQuestion;
        }
        else{
            currentQuestion=0;
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
        if (reserQuestionIsrunning){
            reserQuestionIsrunning = false;
        }

        return returnedValue;
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

    public static Game getInstance() {
        return game;
    }


    // lifeLines
    //Mohammads kod
    public String[] changeQuestion(){
        reserQuestionIsrunning=true;
        String [] returnedResult = {questions[0].getValue(QUESTION_TEXT),questions[0].getValue(OPTION1),questions[0].getValue(OPTION2),questions[0].getValue(OPTION3),questions[0].getValue(OPTION4)};
        return returnedResult;
    }

    //Henriks kod
    //A method when using lifeLine "Call a friend".
    public String callAFriend() {
        boolean answer;
        String friendSays = "";
        int rand = (int) (Math.random()*100);

        // To be able to check the reserve question answer
        byte currentQuestion;
        if (!reserQuestionIsrunning){
            currentQuestion=this.currentQuestion;
        }
        else{
            currentQuestion=0;
        }

        //50% of the times your friend is completely sure what the answer is.
        if (rand > 50) {
            for (char i = 'A'; i<='D';i++) {                                                                                  //looping the chars A to D because the method "checkAnswer()" demand chars.
                answer =  checkAnswer(i);
                if (answer) {
                    friendSays = "Jag är säker på att det är: "+i+ ". " + questions[currentQuestion].getValue((byte)(i-64));  //  Beacuse of "i" is a char and getValue of the answer demand a byte the method subtract 64 using ASCInumbers.
                    break;
                }
            }
        }
        // 25% the friend is pretty sure and guessing at the right answer.
        else if (rand>25) {
            for (char i = 'A'; i<='D';i++) {
                answer =  checkAnswer(i);
                if (answer) {
                    friendSays = "Jag TROR att det är: "+i+ ". " + questions[currentQuestion].getValue((byte)(i-64));
                    break;
                }
            }
        }
        //25% the friend is pretty sure but guessing at the wrong answer.
        else {
            for (char i = 'A'; i<='D';i++) {
                answer =  checkAnswer(i);
                if (!answer) {
                    friendSays = "Jag TROR att det är: "+i+ ". " + questions[currentQuestion].getValue((byte)(i-64));
                    break;
                }
            }

        }

        return "Din vän säger i telefonen:\n"+friendSays;
    }
}