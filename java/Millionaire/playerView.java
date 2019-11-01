import java.util.List;

public interface playerView {

    public void displayErrorMessage(String message);

    public  void displayIntro();

    public void displayQuestion(Question question);

    public void displayAnswer(Answer answer);

    public void displayCallFriendResults();

    public void displayAudienceResults();

    public void displayOneLineMessage(String promptText);

    public boolean getIsFinalAnswer();

    public String getUserName();

}