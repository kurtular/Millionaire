package Millionaire.Model;

import org.json.*;

public class Game implements getJson{
    final private Question[] questions = new Question[16];
    private byte currentQuestion;

    public Game(){
        try {
            getJsonText("http://localhost/millionaire/get/");                                                            // Link to the questions rest api.
            print();
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
    public void print(){//för test skull
        for(int i=0;i<questions.length;i++){
            questions[i].print();
            System.out.println("-----------------------------------------");
        }
    }
}