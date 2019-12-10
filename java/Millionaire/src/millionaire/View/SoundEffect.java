package millionaire.View;

import com.sun.tools.javac.Main;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import millionaire.Timer;

abstract class SoundEffect {
    private static MediaPlayer mediaPlayer;

    final static String INTRO = "intro.mp3";
    final static String PLAY_SCREEN_INTRO = "play_screen_intro.mp3";
    final static String WRONG_ANSWER = "wrong_Answer.mp3";
    final static String CHECKING_ANSWER = "checking_answer.mp3";
    final static String CORRECT_ANSWER = "correct_Answer.mp3";
    final static String CALL_FRIEND = "call_friend.mp3";
    final static String QUESTION_BACKGROUND_SOUND = "question_background_sound.mp3";

//
    static void play(String effectName) {
        try {
            if (mediaPlayer!=null)
                mediaPlayer.stop();
            Timer.delay(()->{
                mediaPlayer = new MediaPlayer( new Media(Main.class.getClassLoader().getResource("sounds/"+effectName).toString()));
                mediaPlayer.play();
            },0.1);
        }catch (Exception e){
            System.err.println("!!Couldn't find the sound file inside sounds folder!!");
        }
    }
    static void stop(){
        mediaPlayer.stop();
    }
}
