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
    public String phoneAFriend() {
        boolean rightAnswer;
        String friendHint = "";
        int rand = (int) (Math.random()*100);
        if (rand > 50) {
            for (char i = 'A'; i<='D';i++) {
                rightAnswer =  checkAnswer(i);
                if (rightAnswer == true) {
                    int j = (int) i-65;
                    friendHint = "Jag är säker på att det är: "+i+ ". " + questions[currentQuestion].getValue((byte)(i-64));
                    break;
                }
            }
        }
        else if (rand>25) {
            for (char i = 'A'; i<='D';i++) {
                rightAnswer =  checkAnswer(i);
                if (rightAnswer == true) {
                    friendHint = "Jag TROR att det är: "+i+ ". " + questions[currentQuestion].getValue((byte)(i-64));
                    break;
                }
            }

        }
        else {
            for (char i = 'A'; i<='D';i++) {
                rightAnswer =  checkAnswer(i);
                if (rightAnswer == false) {
                    friendHint = "Jag TROR att det är: "+i+ ". " + questions[currentQuestion].getValue((byte)(i-64));
                    break;
                }
            }

        }

        return "Din vän säger i telefonen:\n"+friendHint;
    }
    public String askAudience() {
        StringBuilder returnedResult = new StringBuilder(" ");
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