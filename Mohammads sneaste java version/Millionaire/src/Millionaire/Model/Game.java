package Millionaire.Model;

import org.json.*;

public class Game implements getJson{
    // Class variables.
    public final static byte QuestionText=0;
    public final static byte Option1=1;
    public final static byte Option2=2;
    public final static byte Option3=3;
    public final static byte Option4=4;

//The member variables.
    final private Question[] questions = new Question[16];
    private byte currentQuestion;

    public Game(){
        try {
            getJsonText("http://localhost/millionaire/get/");                                                       // Link to the questions rest api.
        }catch (Exception e){
            System.out.println(e);
        }
    }

    // To set questions array from the server this method will called inside getJsonData.
    @Override
    public boolean getJsonArray(String data) {
        JSONArray questions = new JSONArray(data);
        for (int i=0;i<questions.length();i++){
            JSONObject question = questions.getJSONObject(i);
            this.questions[i] = new Question(question.getString("questionText"),toStringArray(question.getJSONArray("options")),question.getInt("token"));
        }
        return false;
    }

// getValue() return a specific string value of the question object depending on value parameter (check class variables above).
    public String getValue(byte value){
        return questions[currentQuestion].getValue(value);
    }

// checkAnswer() will return either true or false depending on the playerAnswer value. ()
    public boolean checkAnswer(char playerAnswer){
        boolean returnedValue=false;
        switch (playerAnswer){
            case 'A':returnedValue = questions[currentQuestion].checkAnswer((byte)1);break;
            case 'B':returnedValue = questions[currentQuestion].checkAnswer((byte)2);break;
            case 'C':returnedValue = questions[currentQuestion].checkAnswer((byte)3);break;
            case 'D':returnedValue = questions[currentQuestion].checkAnswer((byte) 4);break;
            default:returnedValue = false;break;
        }
        return  returnedValue;
    }
    public void nextQuestion(){
        currentQuestion++;
    }
}