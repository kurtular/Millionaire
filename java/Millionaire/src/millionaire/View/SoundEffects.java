package millionaire.View;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

class SoundEffects {

    public MediaPlayer mediaPlayer;
    public static SoundEffects returningSoundEffect;
    public static SoundEffects letsPlay = new SoundEffects("letsPlay");
    public static SoundEffects phoneAFriend = new SoundEffects("phoneAFriend");
    public static SoundEffects mainTheme = new SoundEffects("mainTheme");
    public static SoundEffects intro = new SoundEffects("intro");
    public static SoundEffects wrongAnswer = new SoundEffects("wrongAnswer");

    protected static SoundEffects getInstance(String x ) {
        switch (x) {
            case "mainTheme": {
                returningSoundEffect = mainTheme;
                break;
            }
            case "letsPlay": {
                returningSoundEffect = letsPlay;
                break;
            }
            case "phoneAFriend": {
                returningSoundEffect = phoneAFriend;
                break;
            }
            case "intro": {
                returningSoundEffect = intro;
                break;
            }
            case "wrongAnswer": {
                returningSoundEffect = wrongAnswer;
                break;
            }
            default: {

            }
        }
        return returningSoundEffect;
    }

    SoundEffects(String effectName) {
        String mediaURL = getClass().getResource("/soundeffects/Ljudeffekter/"+effectName+".mp3").toExternalForm();
        Media clip1 = new Media(mediaURL);
        mediaPlayer = new MediaPlayer(clip1);
    }
      public void play() {
       mediaPlayer.play();
    }




}
