package millionaire.View;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LifeLine extends Label {
    public final static String AUDIENCE = "audience";
    public final static String FRIEND = "friend";
    public final static String HALF = "half";
    public final static String CHANGE = "change";

    private ImageView img;
    private String type;

    LifeLine(String type) {
        this.type = type;
        img = new ImageView("img/"+type+".gif");
        img.setFitWidth(150);
        img.setFitHeight(100);
        setGraphic(img);
        setCursor(Cursor.HAND);
    }

//
    public void disable(){
        img.setImage(new Image("img/"+type+"-used.png"));
    }

}
