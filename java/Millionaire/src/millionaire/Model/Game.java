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

    private Game() {
        currentQuestion = 1;
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

    public void nextQuestion() {
        currentQuestion++;
    }

    public void setQuestions() {
        try {
            getJsonText("http://localhost/millionaire/get/");                                                       // Link to the questions rest api.
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static Game getInstance() {
        return game;
    }

    //Henriks kod

    public String callAFriend() {     //A method when using lifeLine Call a friend.
        boolean answer;                // For use when checking if answer is true or false and save it in this.
        String friendSays = "";        //Creating the empty String.
        int rand = (int) (Math.random()*100);   //Get a number between 1 and 100


        if (rand > 50) {                   //50% of the times your friend is completely sure what the answer is.
            for (char i = 'A'; i<='D';i++) {   //looping the chars A to D because the method "checkAnswer()" demand chars.
                answer =  checkAnswer(i);      //Checking if the answer is true and save it into "answer".
                if (answer) {                /* If its true the right answer to current question stores into
                                                "friendSays". Beacuse of "i" is a char and getValue of the answer
                                                demand a byte the method subtract 64 using ASCInumbers. */
                    friendSays = "Jag är säker på att det är: "+i+ ". " + questions[currentQuestion].getValue((byte)(i-64));
                    break;
                }
            }
        }

        else if (rand>25) {                   // 25% the friend is pretty sure and guessing at the right answer.
            for (char i = 'A'; i<='D';i++) {
                answer =  checkAnswer(i);
                if (answer) {
                    friendSays = "Jag TROR att det är: "+i+ ". " + questions[currentQuestion].getValue((byte)(i-64));
                    break;
                }
            }
        }
        else {
            for (char i = 'A'; i<='D';i++) {   //25% the friend is pretty sure but guessing at the wrong answer.
                answer =  checkAnswer(i);
                if (!answer) {
                    friendSays = "Jag TROR att det är: "+i+ ". " + questions[currentQuestion].getValue((byte)(i-64));
                    break;
                }
            }

        }

        return "Din vän säger i telefonen:\n"+friendSays;   //The friend says this.
    }
}