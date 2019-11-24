package Millionaire.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.awt.*;


public class LifeLineArea extends HBox {
    public static LifeLineArea lifeLineArea;
    public static Image img0,img1,img2,img3,img4,img5,img6,img7;
    public static ImageView imgView0, imgView1, imgView2, imgView3;
    private StackPane sPane0, sPane1, sPane2, sPane3;


    private static EventsListner lifeLineAreaListner;

    public LifeLineArea(){
        super();

        img0 = new Image("img/people.gif",150,150,true, true);
        img1 = new Image("img/friend.gif",150,150,true,true);
        img2 = new Image("img/half.gif",150,150,true,true);
        img3 = new Image("img/change.gif",150,150,true,true);
        img4 = new Image("img/people-used.png",150,150,true,true);
        img5 = new Image("img/friend-used.png",150,150,true,true);
        img6 = new Image("img/half-used.png",150,150,true,true);
        img7 = new Image("img/change-used.png",150,150,true,true);


        imgView0 = new ImageView(img0);
        imgView1 = new ImageView(img1);
        imgView2 = new ImageView(img2);
        imgView3 = new ImageView(img3);
        sPane0 = new StackPane(imgView0);
        sPane1 = new StackPane(imgView1);
        sPane2 = new StackPane(imgView2);
        sPane3 = new StackPane(imgView3);
        getChildren().addAll(sPane0,sPane1,sPane2,sPane3);
        setSpacing(30);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5));


        this.setActions();


    }
    //
    protected static void setLifeLineAreaListner(EventsListner listner){
        lifeLineAreaListner = listner;
    }
    //

    private void setActions(){
        sPane0.setOnMouseClicked(event->lifeLineAreaListner.setLifeLine(this,"askThePeople"));
        sPane1.setOnMouseClicked(event->lifeLineAreaListner.setLifeLine(this,"callAFriend"));
        sPane2.setOnMouseClicked(event->lifeLineAreaListner.setLifeLine(this,"removeHalf"));
        sPane3.setOnMouseClicked(event->lifeLineAreaListner.setLifeLine(this,"changeQuestion"));
    }

    public void disableActions(){
        imgView0.setDisable(true);
        imgView1.setDisable(true);
        imgView2.setDisable(true);
        imgView3.setDisable(true);

    }
    //


    //
    protected static LifeLineArea getInstance(){
        if(lifeLineArea == null)
            lifeLineArea=new LifeLineArea();
        return lifeLineArea;
    }
    //
}




