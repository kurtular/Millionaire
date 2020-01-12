package millionaire.View;

import com.sun.tools.javac.Main;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import millionaire.Timer;

/**
 * Abstract class for handling the sounds.
 *
 * @author Henrik , Mohammad.
 */
abstract class SoundEffectPlayer {
    private static MediaPlayer mediaPlayer;

    /**
     * Playing the right sound depending on the effectname and its a part of the View.
     * @param EffectName The different sounds in our library.
     */
    static void play(String EffectName) {
        try {
            if (mediaPlayer!=null)
                mediaPlayer.stop();
            Timer.delay(()->{
                mediaPlayer = new MediaPlayer( new Media(Main.class.getClassLoader().getResource("sounds/"+EffectName).toString()));
                mediaPlayer.play();
            },0.1);
        }catch (Exception e){
            System.err.println("!!Couldn't find the sound file inside sounds folder!!");
        }
    }

    /**
     * Stopping the sound.
     */
    static void stop(){
        mediaPlayer.stop();
    }
}
