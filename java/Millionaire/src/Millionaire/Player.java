import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player {
    String name;
    int time;
    int money;
    int highScore;
    String help [] = new String[3] ;
    public Player(){
        help[0] = "Friend help";
        help[1] = "Audience help";
        help[2] = "50/50";
    }
    public Player(String name, int time,int money, int highScore){
        this.name=name;
        this.time = time;
        this.money = money;
        this.highScore = highScore;
    }
}
